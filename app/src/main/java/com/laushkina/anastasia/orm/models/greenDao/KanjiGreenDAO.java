package com.laushkina.anastasia.orm.models.greenDao;

import com.laushkina.anastasia.orm.models.IKanji;
import com.laushkina.anastasia.orm.models.enums.Popularity;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "Kanjis")
public class KanjiGreenDAO implements IKanji<MeaningGreenDAO, ReadingGreenDAO>{
    @Id
    @Property(nameInDb = "id")
    private String id;

    @ToMany(referencedJoinProperty = "kanjiId")
    private List<MeaningGreenDAO> meanings;

    @ToMany(referencedJoinProperty = "kanjiId")
    private List<ReadingGreenDAO> readings;

    @NotNull
    @Property(nameInDb = "description")
    private String description;

    @NotNull
    @Convert(converter = PopularityConverter.class, columnType = Integer.class)
    @Property(nameInDb = "popularity")
    private Popularity popularity;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1613453278)
    private transient KanjiGreenDAODao myDao;

    @Keep
    public KanjiGreenDAO(String id, String description, Popularity popularity, List<MeaningGreenDAO> meanings,
                         List<ReadingGreenDAO> readings){
        setId(id);
        setDescription(description);
        setPopularity(popularity);
        setReadings(readings);
        setMeanings(meanings);
    }

    @Generated(hash = 451181497)
    public KanjiGreenDAO(String id, @NotNull String description, @NotNull Popularity popularity) {
        this.id = id;
        this.description = description;
        this.popularity = popularity;
    }

    @Generated(hash = 917405895)
    public KanjiGreenDAO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }


    private void setMeanings(List<MeaningGreenDAO> meanings) {
        this.meanings = meanings;
    }

    private void setReadings(List<ReadingGreenDAO> readings) {
        this.readings = readings;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1732639881)
    public List<MeaningGreenDAO> getMeanings() {
        if (meanings == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MeaningGreenDAODao targetDao = daoSession.getMeaningGreenDAODao();
            List<MeaningGreenDAO> meaningsNew = targetDao._queryKanjiGreenDAO_Meanings(id);
            synchronized (this) {
                if (meanings == null) {
                    meanings = meaningsNew;
                }
            }
        }
        return meanings;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 932492683)
    public synchronized void resetMeanings() {
        meanings = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1178469433)
    public List<ReadingGreenDAO> getReadings() {
        if (readings == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ReadingGreenDAODao targetDao = daoSession.getReadingGreenDAODao();
            List<ReadingGreenDAO> readingsNew = targetDao._queryKanjiGreenDAO_Readings(id);
            synchronized (this) {
                if (readings == null) {
                    readings = readingsNew;
                }
            }
        }
        return readings;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1079093920)
    public synchronized void resetReadings() {
        readings = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1170784749)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getKanjiGreenDAODao() : null;
    }
}
