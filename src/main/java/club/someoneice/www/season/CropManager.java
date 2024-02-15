package club.someoneice.www.season;

import club.someoneice.www.season.season.Seasons;
import com.google.common.collect.Maps;
import net.minecraft.block.IGrowable;

import java.util.Arrays;
import java.util.Map;

public class CropManager {
    public static final Map<IGrowable, CropManager> CROP_MANAGER_MAP = Maps.newHashMap();

    public static CropManager registerCropManager(IGrowable growable) {
        CropManager cropManager = new CropManager(growable);
        CROP_MANAGER_MAP.put(growable, cropManager);
        return cropManager;
    }


    public final IGrowable crop;

    private final Map<Integer, CropData> crop_data_map = Maps.newHashMap();

    private CropManager(IGrowable crop) {
        this.crop = crop;
    }

    public CropManager set(int Age, Seasons[] seasons, Seasons[] deathSeasons, float minTemperature, float maxTemperature) {
        this.crop_data_map.put(Age, new CropData(Age, seasons, deathSeasons, minTemperature, maxTemperature));
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
        if (!this.crop_data_map.containsKey(oAge)) return true;
        CropData data = this.crop_data_map.get(age);
        return  temperature >= data.minTemperature && temperature <= data.maxTemperature;
    }

    public boolean canGrow(int age, float temperature, Seasons seasons) {
        boolean flag = checkCanLive(age, temperature);
        if (!flag) return false;

        if (this.crop_data_map.containsKey(age)) {
            CropData data = this.crop_data_map.get(age);
            return Arrays.stream(data.seasons).anyMatch(it -> it == seasons);
        }

        int oAge = 0;
        for (int iAge: this.crop_data_map.keySet()) {
            if (iAge > age) break;
            oAge = iAge;
        }
        if (!this.crop_data_map.containsKey(oAge)) return true;
        CropData data = this.crop_data_map.get(age);
        return Arrays.stream(data.seasons).anyMatch(it -> it == seasons);
    }

    private static final class CropData {
        public final int age;
        public final Seasons[] seasons;
        public final Seasons[] deadSeasons;
        public final float minTemperature;
        public final float maxTemperature;

        public CropData(int age, Seasons[] seasons, Seasons[] deadSeasons, float minTemperature, float maxTemperature) {
            this.age = age;
            this.seasons = seasons;
            this.deadSeasons = deadSeasons;
            this.minTemperature = minTemperature;
            this.maxTemperature = maxTemperature;
        }
    }
}
