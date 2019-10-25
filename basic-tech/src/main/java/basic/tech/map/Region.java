package basic.tech.map;

import java.text.MessageFormat;

/**
 * @description:
 * @author: luolm
 * @createTime： 2019/10/25
 * @version: v1.0.0
 * @history: (版本) 作者 时间 注释
 */
public class Region {
    private String city;
    private String district;

    public Region(String city, String district) {
        this.city = city;
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return MessageFormat.format("'{'city:{0},district:{1}'}'", this.getCity(), this.getDistrict());
    }

    @Override
    public int hashCode() {
        System.out.println(MessageFormat.format("city-{0}-hash-{1};district-{2}-{3}", this.city, this.city.hashCode(), this.district, this.district.hashCode()));
        return this.city.hashCode() + this.district.hashCode();
    }

    @Override
    public boolean equals(Object region) {
        Region region1 = (Region) region;
        return region1.district.equals(this.district) && region1.city.equals(this.city);
    }
}
