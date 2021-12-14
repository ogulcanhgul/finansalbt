package tr.ohg.havadurumu.utils.formatters;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import tr.ohg.havadurumu.R;
import tr.ohg.havadurumu.models.ImmutableWeather;

import static tr.ohg.havadurumu.utils.TimeUtils.isDayTime;

/**
 * Formatter for notification in default Android style with only title, short text and icon.
 */
public class WeatherDefaultNotificationFormatter extends WeatherFormatter {
    /**
     * {@inheritDoc}
     * @throws NullPointerException if {@code weather} is null
     */
    @Override
    public boolean isEnoughValidData(@NonNull ImmutableWeather weather) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");

        return weather.getTemperature() != ImmutableWeather.EMPTY.getTemperature()
                && !weather.getDescription().equals(ImmutableWeather.EMPTY.getDescription())
                && weather.getWeatherIcon() != ImmutableWeather.EMPTY.getWeatherIcon();
    }

    /**
     * Returns temperature with units.
     * @param weather weather info
     * @param temperatureUnit temperature units
     * @param roundedTemperature if {@code true} round temperature and show as integer
     * @return temperature with units
     * @throws NullPointerException if any of parameters is null
     */
    @NonNull
    @Override
    public String getTemperature(@NonNull ImmutableWeather weather,
                                 @NonNull String temperatureUnit,
                                 boolean roundedTemperature
    ) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");
        //noinspection ConstantConditions
        if (temperatureUnit == null)
            throw new NullPointerException("temperatureUnit should not be null");

        return TemperatureFormatter.getTemperature(weather, temperatureUnit, roundedTemperature);
    }

    /**
     * Returns weather description with first uppercase letter.
     * @param weather weather info
     * @return weather description with first uppercase letter
     * @throws NullPointerException if {@code weather} is null
     */
    @NonNull
    @Override
    public String getDescription(@NonNull ImmutableWeather weather) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");

        return DescriptionFormatter.getDescription(weather);
    }

}
