package il.co.mish.model;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class BaseEntity implements Serializable
{
    protected String idfs;
    public BaseEntity()
    {

    }
    public void SetIdfs(String idfs){ this.idfs = idfs; }
    public String GetIdfs(){ return idfs; }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
