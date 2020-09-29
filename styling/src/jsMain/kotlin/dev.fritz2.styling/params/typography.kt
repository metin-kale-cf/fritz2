package dev.fritz2.styling.params

import dev.fritz2.styling.theme.Fonts
import dev.fritz2.styling.theme.Property
import dev.fritz2.styling.theme.theme
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Alias for specific [TextTransformProperty] properties.
 */
typealias TextTransformProperty = Property

/**
 * Predefined values for the [text-transform](https://developer.mozilla.org/de/docs/Web/CSS/text-transform)
 * property. Should be used as expression result in [Typo.textTransform].
 */
object TextTransforms : PropertyValues {
    override val key = "text-transform: "

    const val none: TextTransformProperty = "none"
    const val capitalize: TextTransformProperty = "capitalize"
    const val uppercase: TextTransformProperty = "uppercase"
    const val lowercase: TextTransformProperty = "lowercase"
    const val initial: TextTransformProperty = "initial"
    const val inherit: TextTransformProperty = "inherit"
}

/**
 * Alias for specific [FontStyleProperty] properties.
 */
typealias FontStyleProperty = Property

/**
 * Predefined values for the [font-style](https://developer.mozilla.org/de/docs/Web/CSS/font-style)
 * property. Should be used as expression result in [Typo.fontStyle].
 */
object FontStyles : PropertyValues {
    override val key = "font-style: "

    const val normal: FontStyleProperty = "normal"
    const val italic: FontStyleProperty = "italic"
    const val oblique: FontStyleProperty = "oblique"
    const val initial: FontStyleProperty = "initial"
    const val inherit: FontStyleProperty = "inherit"
}

/**
 * Alias for specific [TextAlignProperty] properties.
 */
typealias  TextAlignProperty = Property

/**
 * Predefined values for the [text-align](https://developer.mozilla.org/de/docs/Web/CSS/text-align)
 * property. Should be used as expression result in [Typo.textAlign].
 */
object TextAligns : PropertyValues {
    override val key = "text-align: "

    const val left: TextAlignProperty = "left"
    const val right: TextAlignProperty = "right"
    const val center: TextAlignProperty = "center"
    const val justify: TextAlignProperty = "justify"
    const val initial: TextAlignProperty = "initial"
    const val inherit: TextAlignProperty = "inherit"
}

/**
 * Alias for specific [FontWeightProperty] properties.
 */
typealias FontWeightProperty = Property

/**
 * Predefined values for the [font-weight](https://developer.mozilla.org/de/docs/Web/CSS/font-weight)
 * property. Should be used as expression result in [Typo.fontWeight].
 */
object FontWeights : PropertyValues {
    override val key = "font-weight: "

    const val normal: FontWeightProperty = "normal"
    const val bold: FontWeightProperty = "bold"
    const val medium: FontWeightProperty = "500"
    const val semiBold: FontWeightProperty = "600"
    const val bolder: FontWeightProperty = "bolder"
    const val lighter: FontWeightProperty = "lighter"
    const val initial: FontWeightProperty = "initial"
    const val inherit: FontWeightProperty = "inherit"
}

internal const val fontSizeKey = "font-size: "
internal const val letterSpacingKey = "letter-spacing: "
internal const val lineHeightKey = "line-height: "


/**
 * This _context_ interface offers functions to style the typography related CSS properties of a component.
 *
 * It offers  functions to define text oriented style properties like font-weight, font-style, etc.

 * There are overrides for all functions that enable one to define the styling for
 * the different media devices independently.
 */
//FIXME: make abstract class to allow inline?
@ExperimentalCoroutinesApi
interface Typo : StyleParams {

    /**
     * This function sets the [font-family](https://developer.mozilla.org/de/docs/Web/CSS/font-family) property.
     *
     * Example call:
     * ```
     * font-family { body }
     * ```
     *
     * @param value extension function parameter for small media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.fonts] that offer the properties of [Fonts]
     */
    fun fontFamily(value: Fonts.() -> Property) = property(fontSizeKey, theme().fonts, value)

    /**
     * This function sets the [font-family](https://developer.mozilla.org/de/docs/Web/CSS/font-family) property
     * for each media device independently.
     *
     * Example call:
     * ```
     * font-family(
     *     sm = { body },
     *     lg = { heading }
     * )
     * ```
     *
     * @param sm extension function parameter for small media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.fonts] that offer the properties of [Fonts]
     * @param md extension function parameter for medium sized media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.fonts] that offer the properties of [Fonts]
     * @param lg extension function parameter for large media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.fonts] that offer the properties of [Fonts]
     * @param xl extension function parameter for extra large media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.fonts] that offer the properties of [Fonts]
     */
    fun fontFamily(
        sm: (Fonts.() -> Property)? = null,
        md: (Fonts.() -> Property)? = null,
        lg: (Fonts.() -> Property)? = null,
        xl: (Fonts.() -> Property)? = null
    ) =
        property(fontSizeKey, theme().fonts, sm, md, lg, xl)

    /**
     * This function sets the [font-size](https://developer.mozilla.org/de/docs/Web/CSS/font-size) property
     *
     * Example call:
     * ```
     * font-size { small }
     * ```
     *
     * @param value extension function parameter for small media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.fontSizes] that offer the properties of [ScaledValue]
     */
    fun fontSize(value: ScaledValueProperty) = property(fontSizeKey, theme().fontSizes, value)

    /**
     * This function sets the [font-size](https://developer.mozilla.org/de/docs/Web/CSS/font-size) property
     * for each media device independently.
     *
     * Example call:
     * ```
     * font-size(
     *     sm = { small },
     *     lg = { normal }
     * )
     * ```
     *
     * @param sm extension function parameter for small media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.fontSizes] that offer the properties of [ScaledValue]
     * @param md extension function parameter for medium sized media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.fontSizes] that offer the properties of [ScaledValue]
     * @param lg extension function parameter for large media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.fontSizes] that offer the properties of [ScaledValue]
     * @param xl extension function parameter for extra large media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.fontSizes] that offer the properties of [ScaledValue]
     */
    fun fontSize(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(fontSizeKey, theme().fontSizes, sm, md, lg, xl)

    /**
     * This function sets the [font-weight](https://developer.mozilla.org/de/docs/Web/CSS/font-weight) property
     *
     * Example call:
     * ```
     * font-weight { bold }
     * ```
     *
     * @param value extension function parameter for small media devices, recommended to use
     *           predefined values from [FontWeights]
     */
    fun fontWeight(value: FontWeights.() -> FontWeightProperty) = property(FontWeights, value)

    /**
     * This function sets the [font-weight](https://developer.mozilla.org/de/docs/Web/CSS/font-weight) property
     * for each media device independently.
     *
     * Example call:
     * ```
     * font-weight(
     *     sm = { normal },
     *     lg = { bold }
     * )
     * ```
     *
     * @param sm extension function parameter for small media devices, recommended to use
     *           predefined values from [FontWeights]
     * @param md extension function parameter for medium sized media devices, recommended to use
     *           predefined values via [FontWeights]
     * @param lg extension function parameter for large media devices, recommended to use
     *           predefined values via [FontWeights]
     * @param xl extension function parameter for extra large media devices, recommended to use
     *           predefined values via [FontWeights]
     */
    fun fontWeight(
        sm: (FontWeights.() -> FontWeightProperty)? = null,
        md: (FontWeights.() -> FontWeightProperty)? = null,
        lg: (FontWeights.() -> FontWeightProperty)? = null,
        xl: (FontWeights.() -> FontWeightProperty)? = null,
    ) =
        property(FontWeights, sm, md, lg, xl)

    /**
     * This function sets the [line-height](https://developer.mozilla.org/de/docs/Web/CSS/line-height) property
     *
     * Example call:
     * ```
     * line-height { small }
     * ```
     *
     * @param value extension function parameter for small media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.lineHeights] that offer the properties of [ScaledValue]
     */
    fun lineHeight(value: ScaledValueProperty) = property(lineHeightKey, theme().lineHeights, value)

    /**
     * This function sets the [line-height](https://developer.mozilla.org/de/docs/Web/CSS/line-height) property
     * for each media device independently.
     *
     * Example call:
     * ```
     * line-height(
     *     sm = { small },
     *     lg = { normal }
     * )
     * ```
     *
     * @param sm extension function parameter for small media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.lineHeights] that offer the properties of [ScaledValue]
     * @param md extension function parameter for medium sized media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.lineHeights] that offer the properties of [ScaledValue]
     * @param lg extension function parameter for large media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.lineHeights] that offer the properties of [ScaledValue]
     * @param xl extension function parameter for extra large media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.lineHeights] that offer the properties of [ScaledValue]
     */
    fun lineHeight(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(lineHeightKey, theme().lineHeights, sm, md, lg, xl)

    /**
     * This function sets the [letter-spacing](https://developer.mozilla.org/de/docs/Web/CSS/letter-spacing) property
     *
     * Example call:
     * ```
     * letter-spacing { small }
     * ```
     *
     * @param value extension function parameter for small media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.letterSpacings] that offer the properties of [ScaledValue]
     */
    fun letterSpacing(value: ScaledValueProperty) = property(letterSpacingKey, theme().letterSpacings, value)

    /**
     * This function sets the [letter-spacing](https://developer.mozilla.org/de/docs/Web/CSS/letter-spacing) property
     * for each media device independently.
     *
     * Example call:
     * ```
     * letter-spacing(
     *     sm = { small },
     *     lg = { normal }
     * )
     * ```
     *
     * @param sm extension function parameter for small media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.letterSpacings] that offer the properties of [ScaledValue]
     * @param md extension function parameter for medium sized media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.letterSpacings] that offer the properties of [ScaledValue]
     * @param lg extension function parameter for large media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.letterSpacings] that offer the properties of [ScaledValue]
     * @param xl extension function parameter for extra large media devices, recommended to use
     *           predefined values via [dev.fritz2.styling.theme.Theme.letterSpacings] that offer the properties of [ScaledValue]
     */
    fun letterSpacing(
        sm: ScaledValueProperty? = null,
        md: ScaledValueProperty? = null,
        lg: ScaledValueProperty? = null,
        xl: ScaledValueProperty? = null
    ) =
        property(letterSpacingKey, theme().letterSpacings, sm, md, lg, xl)

    /**
     * This function sets the [text-align](https://developer.mozilla.org/de/docs/Web/CSS/text-align) property
     *
     * Example call:
     * ```
     * text-align { center }
     * ```
     *
     * @param value extension function parameter for small media devices, recommended to use
     *           predefined values from [TextAligns]
     */
    fun textAlign(value: TextAligns.() -> TextAlignProperty) = property(TextAligns, value)

    /**
     * This function sets the [text-align](https://developer.mozilla.org/de/docs/Web/CSS/text-align) property
     * for each media device independently.
     *
     * Example call:
     * ```
     * text-align(
     *     sm = { center },
     *     lg = { left }
     * )
     * ```
     *
     * @param sm extension function parameter for small media devices, recommended to use
     *           predefined values from [TextAligns]
     * @param md extension function parameter for medium sized media devices, recommended to use
     *           predefined values via [TextAligns]
     * @param lg extension function parameter for large media devices, recommended to use
     *           predefined values via [TextAligns]
     * @param xl extension function parameter for extra large media devices, recommended to use
     *           predefined values via [TextAligns]
     */
    fun textAlign(
        sm: (TextAligns.() -> TextAlignProperty)? = null,
        md: (TextAligns.() -> TextAlignProperty)? = null,
        lg: (TextAligns.() -> TextAlignProperty)? = null,
        xl: (TextAligns.() -> TextAlignProperty)? = null,
    ) =
        property(TextAligns, sm, md, lg, xl)

    /**
     * This function sets the [text-transform](https://developer.mozilla.org/de/docs/Web/CSS/text-transform) property
     *
     * Example call:
     * ```
     * font-transform { capitalize }
     * ```
     *
     * @param value extension function parameter for small media devices, recommended to use
     *           predefined values from [TextTransforms]
     */
    fun textTransform(value: TextTransforms.() -> TextTransformProperty) = property(TextTransforms, value)

    /**
     * This function sets the [text-transform](https://developer.mozilla.org/de/docs/Web/CSS/text-transform) property
     * for each media device independently.
     *
     * Example call:
     * ```
     * font-transform(
     *     sm = { capitalize },
     *     lg = { uppercase }
     * )
     * ```
     *
     * @param sm extension function parameter for small media devices, recommended to use
     *           predefined values from [TextTransforms]
     * @param md extension function parameter for medium sized media devices, recommended to use
     *           predefined values via [TextTransforms]
     * @param lg extension function parameter for large media devices, recommended to use
     *           predefined values via [TextTransforms]
     * @param xl extension function parameter for extra large media devices, recommended to use
     *           predefined values via [TextTransforms]
     */
    fun textTransform(
        sm: (TextTransforms.() -> TextTransformProperty)? = null,
        md: (TextTransforms.() -> TextTransformProperty)? = null,
        lg: (TextTransforms.() -> TextTransformProperty)? = null,
        xl: (TextTransforms.() -> TextTransformProperty)? = null,
    ) =
        property(TextTransforms, sm, md, lg, xl)

    /**
     * This function sets the [font-style](https://developer.mozilla.org/de/docs/Web/CSS/font-style) property
     *
     * Example call:
     * ```
     * font-style { normal }
     * ```
     *
     * @param value extension function parameter for small media devices, recommended to use
     *           predefined values from [FontStyles]
     */
    fun fontStyle(value: FontStyles.() -> FontStyleProperty) = property(FontStyles, value)

    /**
     * This function sets the [font-style](https://developer.mozilla.org/de/docs/Web/CSS/font-style) property
     * for each media device independently.
     *
     * Example call:
     * ```
     * font-style(
     *     sm = { normal },
     *     lg = { italic }
     * )
     * ```
     *
     * @param sm extension function parameter for small media devices, recommended to use
     *           predefined values from [FontStyles]
     * @param md extension function parameter for medium sized media devices, recommended to use
     *           predefined values via [FontStyles]
     * @param lg extension function parameter for large media devices, recommended to use
     *           predefined values via [FontStyles]
     * @param xl extension function parameter for extra large media devices, recommended to use
     *           predefined values via [FontStyles]
     */
    fun fontStyle(
        sm: (FontStyles.() -> FontStyleProperty)? = null,
        md: (FontStyles.() -> FontStyleProperty)? = null,
        lg: (FontStyles.() -> FontStyleProperty)? = null,
        xl: (FontStyles.() -> FontStyleProperty)? = null,
    ) =
        property(FontStyles, sm, md, lg, xl)
}