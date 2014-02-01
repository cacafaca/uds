/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package universaldistributedsystem.plugin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public class PluginsList implements Serializable{
    private List<PluginInfo> jobs = new ArrayList<PluginInfo>();

    public void add(PluginInfo job){
        jobs.add(job);
    }

    public PluginInfo get(int index){
        return jobs.get(index);
    }

    public int size(){
        return jobs.size();
    }

    public Object getJob(){
        return null;
    }

    public String[] getColumnNames(){
        String[] columnNames = {"Name", "Description", "Version", "Size", "Date" };
        return columnNames;
    }

    public Object[][] getJobsListData(){
        Object[][] data = new Object[size()][5];
        for(int i = 0; i < size(); i++){
            PluginInfo ji = get(i);
            data[i][0] = ji.getName();
            data[i][1] = ji.getDescription();
            data[i][2] = ji.getVersion();
            data[i][3] = ji.getSize();
            data[i][4] = ji.getDate();
        }
        return data;
    }
}