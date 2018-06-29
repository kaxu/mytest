package com.kaxudodo.others;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by aaron on 16/11/1.
 */
public class CreateSql {

    public static void main(String[] args) {
//        System.out.println("hello");
        Map<Integer,String> map = new TreeMap<Integer,String>();
        map.put(77,"假体隆胸\t自体脂肪隆胸");
        map.put(78,"吸脂去副乳\t手术去副乳");
        map.put(79,"乳房缩小\t胸部修复\t乳头整形\t乳晕漂红");
        map.put(80,"激光祛痘祛痘印\t激光点痣");
        map.put(81,"黑脸娃娃\t手术去疤\t钻石微雕");
        map.put(82,"美白嫩肤\tE光嫩肤\t美白针\t水光针\t白瓷娃娃\t黄金微针\t射频微针\t水氧活肤\t红蓝光美肤\t化学剥脱祛斑\t激光去红血丝\t激光美肤\t水光娃娃\t超微小气泡\t像素激光\t注射去疤\t镭射净肤\t水密码护理\t无针水光针\t种植面膜\t美白导入\t精华导入\t果酸焕肤\t青春解码仪美肤\t激光祛斑\t补水嫩肤\t皮秒激光\tRV极速美塑美肤\t光子嫩肤");
        map.put(83,"热玛吉\t童颜针\tSMAS除皱术\t射频紧肤\tPRP自体血清美肤\t射频美肤\t超声刀\t深蓝射频\t热提拉\tV颜电波");
        map.put(84,"激光溶脂瘦手臂\t激光溶脂瘦腰部\t激光溶脂瘦腹部\t激光溶脂瘦大腿\t激光溶脂瘦臀部\t激光溶脂瘦小腿\t激光溶脂瘦肩膀\t激光溶脂瘦背部");
        map.put(85,"冷冻溶脂瘦腹部\t冷冻溶脂瘦手臂\t冷冻溶脂瘦腰部\t冷冻溶脂瘦大腿\t冷冻溶脂瘦臀部\t冷冻溶脂瘦小腿\t冷冻溶脂瘦肩膀\t冷冻溶脂瘦背部");
        map.put(86,"吸脂瘦手臂\t吸脂瘦腰部\t吸脂瘦腹部\t吸脂瘦大腿\t吸脂瘦小腿\t吸脂瘦背部\t吸脂瘦肩膀\t吸脂瘦臀部");
        map.put(87,"自体脂肪丰臀\t自体脂肪填充卧蚕\t自体脂肪填充额头\t自体脂肪填充太阳穴\t自体脂肪丰唇\t自体脂肪隆胸\t自体脂肪填充全脸\t自体脂肪填充法令纹\t自体脂肪垫下巴\t自体脂肪隆鼻\t自体脂肪填充脸颊\t自体脂肪填充泪沟\t自体脂肪填充苹果肌\t自体脂肪填充面颊");
        map.put(88,"小腿神经阻断术\t自体脂肪丰臀\t假体丰臀\t腹壁成形术\t埋线减肥\t针灸减肥\t中医减肥");
        map.put(89,"脱腋毛\t脱唇毛\t脱发际线\t脱手臂\t脱大小腿\t私密脱毛\t比基尼线脱毛\t脱手脚毛\t脱背部");
        map.put(90,"冷光美白\t烤瓷牙\t全瓷牙\t贴片美白\t美容冠");
        map.put(91,"种植牙\t补牙\t拔智齿");
        map.put(92,"牙齿矫正\t隐适美\t洗牙\t牙齿综合");
        map.put(93,"招风耳矫正");
        map.put(94,"玻尿酸丰耳垂");
        map.put(95,"手术紧缩阴道\t激光紧缩阴道\t小阴唇手术\t阴蒂提升术\t私密漂红");
        map.put(96,"种植眉毛\t种植睫毛\t种植发际线\t种植鬓角\t种植胡须\t种植胸毛\t种植私密毛发\t种植头顶毛发");
        map.put(97,"半永久纹眉\t半永久睫毛线\t半永久纹唇\t半永久纹发际线\t半永久美瞳线\t半永久纹眼线");
        map.put(98,"宫颈癌疫苗\t联合治疗\t变性手术");

        for(Integer id:map.keySet()){
            String aa = map.get(id);
            String[] a = aa.split("\t");
            for (int i = 0; i < a.length; i++) {
                System.out.println(bind("",a[i],id,i));
            }
        }


    }

    public static String bind(String code,String name,Integer fatherid,int seq){
        StringBuffer buffer = new StringBuffer();
        buffer.append("  INSERT INTO ym.ym_item_info (name, father_id, create_time, is_valid, item_code, level, part, seq, update_time)  ");
        buffer.append("VALUES ('");
        buffer.append(name);
        buffer.append("',");
        buffer.append(fatherid);
        buffer.append(", '2016-10-13 18:38:48', 1, '");
        buffer.append(code);
        buffer.append("', null, null, ");
        buffer.append(seq);
        buffer.append(", '2016-10-13 18:39:00') ;   ");
        return buffer.toString();
    }
}
