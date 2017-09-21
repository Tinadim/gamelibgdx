package com.reis.game.scene.dialog;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;

import java.util.List;

/**
 * Imported and adapted from AndEngine.
 */

public class FontUtils {

    private static final int UNSPECIFIED = -1;

    public static float measureText(final BitmapFont font, final CharSequence text) {
        return FontUtils.measureText(font, text, null);
    }

    public static float measureText(final BitmapFont font, final CharSequence text, final int start, final int end) {
        return FontUtils.measureText(font, text, start, end, null);
    }

    public static float measureText(final BitmapFont font, final CharSequence text, final float[] widths) {
        return FontUtils.measureText(font, text, 0, text.length(), widths);
    }

    public static float measureText(final BitmapFont font, final CharSequence text, final int start, final int end, final float[] widths) {
        final int textLength = end - start;
		/* Early exits. */
        if(start == end) {
            return 0;
        } else if (textLength == 1) {
            return font.getData().getGlyph(text.charAt(start)).width;
        }

        Glyph previousLetter = null;
        float width = 0;
        for (int pos = start, i = 0; pos < end; pos++, i++) {
            final Glyph letter = font.getData().getGlyph(text.charAt(pos));
            if (previousLetter != null) {
                width += previousLetter.getKerning((char)letter.id);
            }
            previousLetter = letter;

			/* Check if this is the last character. */
            if (pos == (end - 1)) {
                width += letter.xoffset + letter.width;
            } else {
                width += letter.xadvance;
            }

            if (widths != null) {
                widths[i] = width;
            }
        }
        return width;
    }

    public static <L extends List<CharSequence>> L splitLines(final BitmapFont font, final CharSequence text, final L result, final AutoWrap autoWrap, final float autoWrapWidth) {
        /**
         * TODO In order to respect already existing linebreaks, {@link FontUtils#split(CharSequence, List)} could be leveraged and than the following methods could be called for each line.
         */
        switch(autoWrap) {
            case LETTERS:
                return FontUtils.splitLinesByLetters(font, text, result, autoWrapWidth);
            case WORDS:
                return FontUtils.splitLinesByWords(font, text, result, autoWrapWidth);
            case CJK:
                return FontUtils.splitLinesByCJK(font, text, result, autoWrapWidth);
            case NONE:
            default:
                throw new IllegalArgumentException("Unexpected " + AutoWrap.class.getSimpleName() + ": '" + autoWrap + "'.");
        }
    }

    private static <L extends List<CharSequence>> L splitLinesByLetters(final BitmapFont font, final CharSequence text, final L result, final float autoWrapWidth) {
        final int textLength = text.length();

        int lineStart = 0;
        int lineEnd = 0;
        int lastNonWhitespace = 0;
        boolean charsAvailable = false;

        for(int i = 0; i < textLength; i++) {
            final char character = text.charAt(i);
            if (character != ' ') {
                if (charsAvailable) {
                    lastNonWhitespace = i + 1;
                } else {
                    charsAvailable = true;
                    lineStart = i;
                    lastNonWhitespace = lineStart + 1;
                    lineEnd = lastNonWhitespace;
                }
            }

            if (charsAvailable) {
//				/* Just for debugging. */
//				final CharSequence line = pText.subSequence(lineStart, lineEnd);
//				final float lineWidth = FontUtils.measureText(pFont, pText, lineStart, lineEnd);
//
//				final CharSequence lookaheadLine = pText.subSequence(lineStart, lastNonWhitespace);
                final float lookaheadLineWidth = FontUtils.measureText(font, text, lineStart, lastNonWhitespace);

                final boolean isEndReached = (i == (textLength - 1));
                if (isEndReached) {
					/* When the end of the string is reached, add remainder to result. */
                    if (lookaheadLineWidth <= autoWrapWidth) {
                        result.add(text.subSequence(lineStart, lastNonWhitespace));
                    } else {
                        result.add(text.subSequence(lineStart, lineEnd));
						/* Avoid special case where last line is added twice. */
                        if(lineStart != i) {
                            result.add(text.subSequence(i, lastNonWhitespace));
                        }
                    }
                } else {
                    if (lookaheadLineWidth <= autoWrapWidth) {
                        lineEnd = lastNonWhitespace;
                    } else {
                        result.add(text.subSequence(lineStart, lineEnd));
                        i = lineEnd - 1;
                        charsAvailable = false;
                    }
                }
            }
        }

        return result;
    }

    private static <L extends List<CharSequence>> L splitLinesByWords(final BitmapFont font, final CharSequence text, final L result, final float autoWrapWidth) {
        final int textLength = text.length();

        if (textLength == 0) {
            return result;
        }

        final float spaceWidth = font.getData().getGlyph(' ').xadvance;

        int lastWordEnd = FontUtils.UNSPECIFIED;
        int lineStart = FontUtils.UNSPECIFIED;
        int lineEnd = FontUtils.UNSPECIFIED;

        float lineWidthRemaining = autoWrapWidth;
        boolean firstWordInLine = true;
        int i = 0;
        while (i < textLength) {
            int spacesSkipped = 0;
			/* Find next word. */
            { /* Skip whitespaces. */
                while((i < textLength) && (text.charAt(i) == ' ')) {
                    i++;
                    spacesSkipped++;
                }
            }
            final int wordStart = i;

			/* Mark beginning of a new line. */
            if (lineStart == FontUtils.UNSPECIFIED) {
                lineStart = wordStart;
            }

            { /* Skip non-whitespaces. */
                while((i < textLength) && (text.charAt(i) != ' ')) {
                    i++;
                }
            }
            final int wordEnd = i;

			/* Nothing more could be read. */
            if (wordStart == wordEnd) {
                if(!firstWordInLine) {
                    result.add(text.subSequence(lineStart, lineEnd));
                }
                break;
            }

//			/* Just for debugging. */
//			final CharSequence word = pText.subSequence(wordStart, wordEnd);

            final float wordWidth = FontUtils.measureText(font, text, wordStart, wordEnd);

			/* Determine the width actually needed for the current word. */
            final float widthNeeded;
            if(firstWordInLine) {
                widthNeeded = wordWidth;
            } else {
                widthNeeded = (spacesSkipped * spaceWidth) + wordWidth;
            }

			/* Check if the word fits into the rest of the line. */
            if (widthNeeded <= lineWidthRemaining) {
                if (firstWordInLine) {
                    firstWordInLine = false;
                } else {
                    lineWidthRemaining -= FontUtils.getAdvanceCorrection(font, text, lastWordEnd - 1);
                }
                lineWidthRemaining -= widthNeeded;
                lastWordEnd = wordEnd;
                lineEnd = wordEnd;

				/* Check if the end was reached. */
                if (wordEnd == textLength) {
                    result.add(text.subSequence(lineStart, lineEnd));
					/* Added the last line. */
                    break;
                }
            } else {
				/* Special case for lines with only one word. */
                if (firstWordInLine) {
					/* Check for lines that are just too big. */
                    if (wordWidth >= autoWrapWidth) {
                        result.add(text.subSequence(wordStart, wordEnd));
                        lineWidthRemaining = autoWrapWidth;
                    } else {
                        lineWidthRemaining = autoWrapWidth - wordWidth;

						/* Check if the end was reached. */
                        if(wordEnd == textLength) {
                            result.add(text.subSequence(wordStart, wordEnd));
							/* Added the last line. */
                            break;
                        }
                    }

					/* Start a completely new line. */
                    firstWordInLine = true;
                    lastWordEnd = FontUtils.UNSPECIFIED;
                    lineStart = FontUtils.UNSPECIFIED;
                    lineEnd = FontUtils.UNSPECIFIED;
                } else {
					/* Finish the current line. */
                    result.add(text.subSequence(lineStart, lineEnd));

					/* Check if the end was reached. */
                    if (wordEnd == textLength) {
						/* Add the last word. */
                        result.add(text.subSequence(wordStart, wordEnd)); // TODO Does this cover all cases?
                        break;
                    } else {
						/* Start a new line, carrying over the current word. */
                        lineWidthRemaining = autoWrapWidth - wordWidth;
                        firstWordInLine = false;
                        lastWordEnd = wordEnd;
                        lineStart = wordStart;
                        lineEnd = wordEnd;
                    }
                }
            }
        }
        return result;
    }

    private static <L extends List<CharSequence>> L splitLinesByCJK(final BitmapFont font, final CharSequence text, final L result, final float autoWrapWidth) {
        final int textLength = text.length();

        int lineStart = 0;
        int lineEnd = 0;

		/* Skip whitespaces at the beginning of the string. */
        while ((lineStart < textLength) && (text.charAt(lineStart) == ' ')) {
            lineStart++;
            lineEnd++;
        }

        int i = lineEnd;
        while (i < textLength) {
            lineStart = lineEnd;

            { /* Look for a sub string */
                boolean charsAvailable = true;
                while(i < textLength) {

                    { /* Skip whitespaces at the end of the string */
                        int j = lineEnd;
                        while (j < textLength) {
                            if (text.charAt(j) == ' ' ) {
                                j++;
                            }
                            else {
                                break;
                            }
                        }
                        if (j == textLength) {
                            if (lineStart == lineEnd) {
                                charsAvailable = false;
                            }
                            i = textLength;
                            break;
                        }
                    }

                    lineEnd++;

                    final float lineWidth = FontUtils.measureText(font, text, lineStart, lineEnd);

                    if (lineWidth > autoWrapWidth) {
                        if (lineStart < lineEnd - 1) {
                            lineEnd--;
                        }

                        result.add(text.subSequence(lineStart, lineEnd));
                        charsAvailable = false;
                        i = lineEnd;
                        break;
                    }
                    i = lineEnd;
                }

                if (charsAvailable) {
                    result.add(text.subSequence(lineStart, lineEnd));
                }
            }
        }

        return result;
    }

    private static float getAdvanceCorrection(final BitmapFont pFont, final CharSequence pText, final int pIndex) {
        final Glyph lastWordLastLetter = pFont.getData().getGlyph(pText.charAt(pIndex));
        return -(lastWordLastLetter.xoffset + lastWordLastLetter.width) + lastWordLastLetter.xadvance;
    }
}
