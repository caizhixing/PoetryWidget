package com.caizhixing.poetrywidget;

import android.os.Parcel;
import android.os.Parcelable;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created on 2019/2/11.
 * Author caizhixing
 */
@Entity
public class Poetry implements Parcelable {
    @Id(autoincrement = true)
    private Long id;
    private String displayContent;
    private String title;
    private String dynasty;
    private String author;
    private String content;

    protected Poetry(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        displayContent = in.readString();
        title = in.readString();
        dynasty = in.readString();
        author = in.readString();
        content = in.readString();
    }

    @Generated(hash = 295670411)
    public Poetry(Long id, String displayContent, String title, String dynasty,
            String author, String content) {
        this.id = id;
        this.displayContent = displayContent;
        this.title = title;
        this.dynasty = dynasty;
        this.author = author;
        this.content = content;
    }

    @Generated(hash = 1973469202)
    public Poetry() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(displayContent);
        dest.writeString(title);
        dest.writeString(dynasty);
        dest.writeString(author);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayContent() {
        return this.displayContent;
    }

    public void setDisplayContent(String displayContent) {
        this.displayContent = displayContent;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDynasty() {
        return this.dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static final Creator<Poetry> CREATOR = new Creator<Poetry>() {
        @Override
        public Poetry createFromParcel(Parcel in) {
            return new Poetry(in);
        }

        @Override
        public Poetry[] newArray(int size) {
            return new Poetry[size];
        }
    };
}
