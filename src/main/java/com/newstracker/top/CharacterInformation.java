package com.newstracker.top;

import java.util.Objects;

public class CharacterInformation {

    int pvpLevel;
    int petCount;
    String guildName;

    public CharacterInformation(int pvpLevel, int petCount, String guildName) {
        this.pvpLevel = pvpLevel;
        this.petCount = petCount;
        this.guildName = guildName;
    }

    public int getPvpLevel() {
        return pvpLevel;
    }

    public void setPvpLevel(int pvpLevel) {
        this.pvpLevel = pvpLevel;
    }

    public int getPetCount() {
        return petCount;
    }

    public void setPetCount(int petCount) {
        this.petCount = petCount;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterInformation that = (CharacterInformation) o;
        return pvpLevel == that.pvpLevel &&
                petCount == that.petCount &&
                Objects.equals(guildName, that.guildName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pvpLevel, petCount, guildName);
    }

    @Override
    public String toString() {
        return "CharacterInformation{" +
                "pvpLevel=" + pvpLevel +
                ", petCount=" + petCount +
                ", guildName='" + guildName + '\'' +
                '}';
    }
}
