package it.appnote;
import android.app.*;import android.appwidget.*;import android.content.*;import android.widget.*;import android.net.Uri;
public class Widget extends AppWidgetProvider {
 static void refresh(Context c){AppWidgetManager m=AppWidgetManager.getInstance(c);int[] ids=m.getAppWidgetIds(new ComponentName(c,Widget.class));for(int id:ids) update(c,m,id);m.notifyAppWidgetViewDataChanged(ids,R.id.list);}
 static void update(Context c,AppWidgetManager m,int id){RemoteViews v=new RemoteViews(c.getPackageName(),R.layout.widget);Intent s=new Intent(c,Rows.class).setData(Uri.parse("appnote://widget/"+id));v.setRemoteAdapter(R.id.list,s);v.setOnClickPendingIntent(R.id.add,PendingIntent.getActivity(c,id,new Intent(c,Editor.class),PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE));v.setPendingIntentTemplate(R.id.list,PendingIntent.getActivity(c,id,new Intent(c,Editor.class).setAction("edit"),PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_MUTABLE));m.updateAppWidget(id,v);}
 @Override public void onUpdate(Context c,AppWidgetManager m,int[] ids){for(int id:ids)update(c,m,id);m.notifyAppWidgetViewDataChanged(ids,R.id.list);}
 @Override public void onReceive(Context c,Intent i){super.onReceive(c,i);if(!AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(i.getAction()))refresh(c);}
}
