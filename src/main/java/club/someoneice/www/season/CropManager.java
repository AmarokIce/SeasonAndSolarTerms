package club.someoneice.www.season;

import club.someoneice.www.season.season.Season;
import com.google.common.collect.Maps;
import net.minecraft.block.IGrowable;

import java.util.Arrays;
import java.util.Map;

public class CropManager {
    public static final Map<IGrowable, CropManager> CROP_MANAGER_MAP = Maps.newHashMap();

    public final IGrowable crop;

    private final Map<Integer, CropData> crop_data_map = Maps.newHashMap();

    public CropManager(IGrowable crop) {
        this.crop = crop;
    }

    public CropManager set(int Age, Season[] seasons, float minTemperature, float maxTemperature) {
        this.crop_data_map.put(Age, new CropData(Age, seasons, minTemperature, maxTemperature));
        return this;
    }

    public boolean checkCanLive(int age, float temperature) {
        if (this.crop_data_map.containsKey(age)) {
            CropData data = this.crop_data_map.get(age);
            return  temperature >= data.minTemperature && temperature <= data.maxTemperature;
        }

        int oAge = 0;
        for (int iAge: this.crop_data_map.keySet()) {
            if (iAge > age) break;
            oAge = iAge;
        }
        CropData data = this.crop_data_map.get(oAge);
        return temperature >= data.minTemperature && temperature <= data.maxTemperature;
    }

    public boolean canGrow(int age, float temperature, Season season) {
        boolean flag = checkCanLive(age, temperature);
        if (!flag) return false;

        if (this.crop_data_map.containsKey(age)) {
            CropData data = this.crop_data_map.get(age);
            return Arrays.stream(data.seasons).anyMatch(it -> it == season);
        }

        int oAge = 0;
        for (int iAge: this.crop_data_map.keySet()) {
            if (iAge > age) break;
            oAge = iAge;
        }
        CropData data = this.crop_data_map.get(oAge);
        return Arrays.stream(data.seasons).anyMatch(it -> it == season);
    }

    private static final class CropData {
        public final int age;
        public final Season[] seasons;
        public final Season[] deadSeason;
        public final float minTemperature;
        public final float maxTemperature;

        public CropData(int age, Season[] seasons, Season[] deadSeason, float minTemperature, float maxTemperature) {
            this.age = age;
            this.seasons = seasons;
            this.deadSeason = deadSeason;
            this.minTemperature = minTemperature;
            this.maxTemperature = maxTemperature;
        }
    }
}
