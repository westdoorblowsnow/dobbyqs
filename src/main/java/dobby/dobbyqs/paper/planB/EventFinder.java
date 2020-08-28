package dobby.dobbyqs.paper.planB;

public abstract class EventFinder {
    protected String str;
    protected int index;
    protected final Context context;
    public abstract Event nextEvent();
    public EventFinder(String str,Context context) {
        this.str = str;
        this.context = context;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventFinder{");
        sb.append("str='").append(str).append('\'');
        sb.append(", index=").append(index);
        sb.append(", context=").append(context);
        sb.append('}');
        return sb.toString();
    }
}
