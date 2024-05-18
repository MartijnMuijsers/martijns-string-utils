/*
 * © Martijn Muijsers <martijnmuijsers@live.nl> 2018-2024.
 * Licensed under AGPLv3.
 */
package nl.martijnmuijsers.stringutils;

import java.nio.charset.Charset;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsefulCharsets {
    public static Charset GB18030 = java.nio.charset.Charset.forName("GB18030");
}
