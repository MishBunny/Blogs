package il.co.mish.model;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class BlogPost extends BaseEntity implements Serializable
{
    private String author;
    private String title;
    private String content;
    private Long date;
    private String idf;
    public BlogPost(){}

    public BlogPost(String author, String title, String content,Long date)
    {
        this.author=author;
        this.title=title;
        this.content=content;
        this.date = date;
    }


    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author=author; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title=title; }


    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Long getDate() { return date; }

    public void setDate(Long date) { this.date = date; }

    public void setIdFs(String doc){}

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
