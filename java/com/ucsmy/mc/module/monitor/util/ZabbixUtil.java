package com.ucsmy.mc.module.monitor.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.ucsmy.mc.util.SpringContextUtil;
import com.zabbix4j.ZabbixApi;
import com.zabbix4j.ZabbixApiException;
import com.zabbix4j.history.HistoryGetRequest;
import com.zabbix4j.history.HistoryGetResponse;
import com.zabbix4j.history.HistoryObject;
import com.zabbix4j.host.HostGetRequest;
import com.zabbix4j.host.HostGetResponse;
import com.zabbix4j.item.ItemGetRequest;
import com.zabbix4j.item.ItemGetResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by ucs_mawenzhong on 2017/6/27.
 */
public class ZabbixUtil {
    private static ZabbixApi zabbixApi = null;
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static {
        Properties properties = SpringContextUtil.getPropertiesBean();
        String user = properties.getProperty("zabbix.user");
        String password = properties.getProperty("zabbix.password");
        String url = properties.getProperty("zabbix.url");

//        String user = "test";
//        String password = "ucsmy1234";
//        String url = "http://10.1.20.253/api_jsonrpc.php";

        zabbixApi = new ZabbixApi(url);
        try {
            zabbixApi.login(user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //loadConfig();
    }

    /*
    private static Map<String, String> monitorMap;
    private static void loadConfig() {
        monitorMap = new HashMap<>();
        Resource resource = new ClassPathResource("/monitor");
        File excelExport = null;
        try {
            excelExport = resource.getFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (excelExport == null) return;
        File[] listFiles = excelExport.listFiles();
        if (listFiles == null) return;
        for (File file: listFiles) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            if (fileInputStream != null) {
                try {
                    String configStr = IOUtils.toString(fileInputStream, Charset.forName("utf-8"));
                    //JSONObject configObject = JSON.parseObject(configStr);
                    // configMap.putAll(configObject);
                    String fileName = file.getName().split("\\.")[0];
                    fileName = fileName.replace("+", ":");
                    monitorMap.put(fileName, configStr);
                } catch(IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }


    public static String getHistoryItems(String hostName, String data) {
        return monitorMap.get(hostName + "_" + data);
    }

 */


    /**
     * 指标返回字段
     */
    private static List<String> hostOutput = null;
    static {
        hostOutput = new ArrayList<>();
        hostOutput.add("key_");
        hostOutput.add("name");
        hostOutput.add("lastvalue");
        hostOutput.add("units");
    }

    /**
     * 获取主机的指标
     * @param hostName
     */
    public static List<ItemGetResponse.Result> getItems(String hostName) {
/*

        ItemGetRequest igr = new ItemGetRequest();
        ItemGetRequest.Params igrp = igr.getParams();
        igrp.setHost(hostName); // SA0WD02066 | SA0WC02065 | SC0LW00502
*/


        //Map<String, String> map = new HashMap<>();
        //map.put("status", "0");
        //igrp.setSearch(map);



        MyItemGetRequest igr = new MyItemGetRequest();
        Map<String, Object> params = igr.getParams();
        params.put("host", hostName);
        params.put("output", hostOutput);


        ItemGetResponse itemGetResponse = null;
        try {
            itemGetResponse = zabbixApi.item().get(igr);
        } catch (ZabbixApiException e) {
            e.printStackTrace();
        }

        List<ItemGetResponse.Result> result = itemGetResponse.getResult();
        /*for (int i = 0; i < result.size(); i++) {
            ItemGetResponse.Result item = result.get(i);

            System.out.print(item.getKey_());
            System.out.print(" | ");
            System.out.print(item.getName());
            System.out.print(" | ");
            System.out.println(item.getLastvalue());
            System.out.println(JSON.toJSONString(item));
        }*/
        //System.out.println(JSON.toJSONString(result));
        return result;
    }

    /**
     * 获取主机的历史指标
     * @param hostName
     */
    public static List<ItemGetResponse.Result> getHistoryItems(String hostName, Date data) {
        HostGetRequest hostGetRequest = new HostGetRequest();
        HostGetRequest.Params params = hostGetRequest.getParams();

        Map<String, String> map = new HashMap<>();
        map.put("host", hostName);
        params.setSearch(map);
        params.setOutput("hostid");

        Integer hostId = null;
        try {
            //long s1 = System.currentTimeMillis();
            HostGetResponse hostGetResponse = zabbixApi.host().get(hostGetRequest);
            //System.out.println(System.currentTimeMillis() - s1);

            if (hostGetResponse.getResult().size() <= 0) {
                return Collections.emptyList();
            }

            hostId = hostGetResponse.getResult().get(0).getHostid();

            if (hostId == null) {
                return Collections.emptyList();
            }

        } catch (ZabbixApiException e) {
            e.printStackTrace();
        }


        //int historyCount = 0;
        long time = data.getTime() / 1000;

        List<FutureTask<List<HistoryObject>>> futureTasks = new ArrayList<>();
        for (int k = 0; k < 5; k++) {
            if (k == 2) continue;

            final HistoryGetRequest historyGetRequest = new HistoryGetRequest();
            HistoryGetRequest.Params historyParams = historyGetRequest.getParams();

            List<Integer> hostIds = new ArrayList<>();
            hostIds.add(hostId);
            historyParams.setHostids(hostIds);
            historyParams.setTime_from(time - 3600);
            historyParams.setTime_till(time + 60);
            historyParams.setHistory(k);


            //创建一个异步任务
            FutureTask<List<HistoryObject>> futureTask = new FutureTask<>(new Callable<List<HistoryObject>>() {
                @Override
                public List<HistoryObject> call() throws Exception {
                    HistoryGetResponse historyGetResponse = null;
                    try {
                        //long s1 = System.currentTimeMillis();
                        historyGetResponse = zabbixApi.history().get(historyGetRequest);
                        //System.out.println(System.currentTimeMillis() - s1);

                        return historyGetResponse.getResult();
                    } catch (ZabbixApiException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.out.println("zabbix 返回数据解析出错");
                    }

                    return Collections.emptyList();
                }
            });

            futureTasks.add(futureTask);
            executorService.submit(futureTask);
        }


        Map<Integer, HistoryObject> resultMap = new HashMap<>();
        for (FutureTask<List<HistoryObject>> futureTask : futureTasks) {
            try {
                List<HistoryObject> result = futureTask.get();

                for (int i = 0; i < result.size(); i++) {
                    HistoryObject historyObject = result.get(i);
                    Integer itemId = historyObject.getItemid();
                    HistoryObject preHistoryObject = resultMap.get(itemId);
                    if (preHistoryObject != null) {
                        if (Math.abs(preHistoryObject.getClock() - time) > Math.abs(historyObject.getClock() - time)) {
                            resultMap.put(itemId, historyObject);
                        }
                    } else {
                        resultMap.put(itemId, historyObject);
                    }

                    //System.out.println(JSON.toJSONString(historyObject));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


//        for (HistoryObject historyObject: resultMap.values()) {
//            System.out.println(JSON.toJSONString(historyObject));
//        }

//        System.out.println(historyCount);
//        System.out.println(resultMap.values().size());

        //long s1 = System.currentTimeMillis();
        List<ItemGetResponse.Result> items = ZabbixUtil.getItems(hostName);
        //System.out.println(JSON.toJSONString(items));
        //System.out.println(System.currentTimeMillis() - s1);

        List<ItemGetResponse.Result> itemResult = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            ItemGetResponse.Result item = items.get(i);

            /*
            System.out.print(item.getKey_());
            System.out.print(" | ");
            System.out.print(item.getName());
            System.out.print(" | ");
            System.out.print(item.getLastvalue());
            System.out.print(" | ");
            System.out.print(item.getStatus());
            System.out.print(" | ");
            if (resultMap.get(item.getItemid()) != null) {
                System.out.println(resultMap.get(item.getItemid()).getValue());
                System.out.println(JSON.toJSONString(item));
            } else {
                System.out.println(resultMap.get(item.getItemid()));
                System.out.println(JSON.toJSONString(item));
            }*/

            /*
            if (resultMap.get(item.getItemid()) == null && item.getStatus() == 0) {
                System.out.print(item.getKey_());
                System.out.print(" | ");
                System.out.print(item.getName());
                System.out.print(" | ");
                System.out.print(item.getLastvalue());
                System.out.print(" | ");
                System.out.println(item.getStatus());
                System.out.println(JSON.toJSONString(item));
            }
            */


            HistoryObject historyObject = resultMap.get(item.getItemid());
            if (historyObject != null) {
                item.setLastvalue(historyObject.getValue());
                itemResult.add(item);
            }

        }

        return itemResult;
    }
}
