package it.appnote;
import android.content.*; import org.json.*; import java.util.*; import java.time.*;
class Store {
 static SharedPreferences p(Context c){return c.getSharedPreferences("notes",0);}
 static ArrayList<JSONObject> all(Context c){ArrayList<JSONObject> a=new ArrayList<>();try{JSONArray j=new JSONArray(p(c).getString("items","[]"));for(int i=0;i<j.length();i++)a.add(j.getJSONObject(i));}catch(Exception e){} a.sort(Comparator.comparing(x->x.optString("date", "").isEmpty()?"9999":x.optString("date")));return a;}
 static void save(Context c,JSONObject item,String id){ArrayList<JSONObject>a=all(c);a.removeIf(x->x.optString("id").equals(id));if(item!=null)a.add(item);p(c).edit().putString("items",new JSONArray(a).toString()).apply();Widget.refresh(c);}
}
