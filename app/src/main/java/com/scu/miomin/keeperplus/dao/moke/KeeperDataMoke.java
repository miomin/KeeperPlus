package com.scu.miomin.keeperplus.dao.moke;

/**
 * 描述:Demo版存放临时数据 创建日期:2016/1/21
 *
 * @author 莫绪旻
 */
public class KeeperDataMoke {

//    private static KeeperDataMoke instance;
//
//    private KeeperDataMoke() {
//
//    }
//
//    public static KeeperDataMoke getInstance() {
//        if (instance == null) {
//            synchronized (KeeperDataMoke.class) {
//                if (instance == null)
//                    instance = new KeeperDataMoke();
//            }
//        }
//        return instance;
//    }
//
//    public void initData() {
//        initThreatmentList();
//    }
//
//    public void initThreatmentList() {
//
//        TreatmentBean treatmentFollowupForPatientBean;
//
//        treatmentFollowupForPatientBean = new TreatmentBean("2015-11-24",
//                KeeperPlusCache.getInstance().getFriendByID("18081800000"),
//                KeeperPlusCache.getInstance().getCurrentUser(),
//                "以胸闷、心慌2天来诊，自诉前几天曾熬夜，工作较忙而发病，并觉乏力、纳差。查体：脉搏90次/分，脉率不齐，即查心电图提示：窦性心律不齐，心率88次/分，st-t轻度改变。询问病史，既往体健。考虑：劳累所致心律失常，心肌供血不足。建议患者注意休息，避免劳累，多食富含维生素、蛋白质及微量元素的瓜果蔬菜，如有不适，及时就诊，以免发生严重的心脏疾患。");
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-27", "情况较差", "感觉好些了",
//                        190.4, 110, 87.4, 120.2, 142.2, 178.2, 122, 67, 90));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-30", "情况开始趋于稳定", "感觉好些了",
//                        160.2, 104.5, 96.4, 140.3, 165.3, 165, 162, 23, 56));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-12-01", "有所好转，建议注意康复过程的生活习惯", "感觉好些了",
//                        170.4, 102.4, 105.6, 170.2, 193, 176, 132, 54, 76));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-12-04", "已经好转许多", "感觉好些了",
//                        190, 105.3, 104.3, 120.5, 112, 198, 162, 65, 78));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-12-10", "已经痊愈", "感觉好些了",
//                        187.4, 106.3, 104.5, 133.2, 145, 189, 142, 78, 98));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-12-12", "已经痊愈", "感觉好些了",
//                        185, 143, 132, 135, 134, 143, 134, 67, 89));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-12-13", "已经痊愈", "感觉好些了",
//                        178, 156, 143, 145, 154, 154, 145, 65, 87));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-12-15", "已经痊愈", "感觉好些了",
//                        198, 123, 123, 123, 123, 156, 156, 86, 84));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-12-16", "已经痊愈", "感觉好些了",
//                        178, 156, 143, 145, 154, 154, 145, 65, 87));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-12-18", "已经痊愈", "感觉好些了",
//                        198, 123, 123, 123, 123, 156, 156, 86, 84));
//        KeeperPlusCache.getInstance().addTreatment(treatmentFollowupForPatientBean);
//
//
//        treatmentFollowupForPatientBean = new TreatmentBean("2015-11-19",
//                KeeperPlusCache.getInstance().getFriendByID("18081800001"),
//                KeeperPlusCache.getInstance().getCurrentUser(),
//                "感冒数日后觉心慌、乏力，活动后气促来诊，查体：T38度，脉搏110次/分，有停顿。即查心电图：窦性心动过速并不齐，心率112次/分，偶发房性早搏，st段改变。考虑有急性心肌炎可能，建议住院观察。");
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-21", "情况较差", "感觉好些了",
//                        190.4, 110, 87.4, 120.2, 142.2, 178.2, 122, 67, 90));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-22", "情况开始趋于稳定", "感觉好些了",
//                        160.2, 104.5, 96.4, 140.3, 165.3, 165, 162, 23, 56));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-25", "有所好转，建议注意康复过程的生活习惯", "感觉好些了",
//                        170.4, 102.4, 105.6, 170.2, 193, 176, 132, 54, 76));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-26", "已经好转许多", "感觉好些了",
//                        190, 105.3, 104.3, 120.5, 112, 198, 162, 65, 78));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-27", "已经痊愈", "感觉好些了",
//                        187.4, 106.3, 104.5, 133.2, 145, 189, 142, 78, 98));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-28", "已经痊愈", "感觉好些了",
//                        185, 143, 132, 135, 134, 143, 134, 67, 89));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-29", "已经痊愈", "感觉好些了",
//                        178, 156, 143, 145, 154, 154, 145, 65, 87));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-30", "已经痊愈", "感觉好些了",
//                        198, 123, 123, 123, 123, 156, 156, 86, 84));
//        KeeperPlusCache.getInstance().addTreatment(treatmentFollowupForPatientBean);
//
//
//
//        treatmentFollowupForPatientBean = new TreatmentBean("2015-11-15",
//                KeeperPlusCache.getInstance().getFriendByID("18081800002"),
//                KeeperPlusCache.getInstance().getCurrentUser(),
//                "因心前区疼痛，加重伴呼吸困难10小时来诊。反复感心前区疼痛，系膨胀性或压迫感，多于劳累、饭后发作，每次持续3～5分钟，休息后减轻。入院前2月，痛渐频繁，且休息时也发作，入院前10小时，于睡眠中突感心前区剧痛，并向左肩部、臂部放射，且伴大汗、呼吸困难，咳出少量粉红色泡沫状痰液，自含消心痛无缓解。");
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-16", "情况较差", "感觉好些了",
//                        190.4, 110, 87.4, 120.2, 142.2, 178.2, 122, 67, 90));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-17", "情况开始趋于稳定", "感觉好些了",
//                        160.2, 104.5, 96.4, 140.3, 165.3, 165, 162, 23, 56));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-18", "有所好转，建议注意康复过程的生活习惯", "感觉好些了",
//                        170.4, 102.4, 105.6, 170.2, 193, 176, 132, 54, 76));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-19", "已经好转许多", "感觉好些了",
//                        190, 105.3, 104.3, 120.5, 112, 198, 162, 65, 78));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-20", "已经痊愈", "感觉好些了",
//                        187.4, 106.3, 104.5, 133.2, 145, 189, 142, 78, 98));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-21", "已经痊愈", "感觉好些了",
//                        185, 143, 132, 135, 134, 143, 134, 67, 89));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-22", "已经痊愈", "感觉好些了",
//                        178, 156, 143, 145, 154, 154, 145, 65, 87));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-23", "已经痊愈", "感觉好些了",
//                        198, 123, 123, 123, 123, 156, 156, 86, 84));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-24", "已经痊愈", "感觉好些了",
//                        178, 156, 143, 145, 154, 154, 145, 65, 87));
//        KeeperPlusCache.getInstance().addTreatment(treatmentFollowupForPatientBean);
//
//
//        treatmentFollowupForPatientBean = new TreatmentBean("2015-11-11",
//                KeeperPlusCache.getInstance().getFriendByID("18081800003"),
//                KeeperPlusCache.getInstance().getCurrentUser(),
//                "经常活动后气急，1小时前上体育课时觉胸闷，气急，头晕，乏力，老师送来就诊，查见：面唇发绀，汗出肢冷，心脏听诊：心率120次/分，二尖瓣区可闻收缩期吹风样杂音，查心电图：窦性心动过速，st—t改变，考虑先天性心脏病，建议入院进一步诊治。");
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-12", "情况较差", "感觉好些了",
//                        190.4, 110, 87.4, 120.2, 142.2, 178.2, 122, 67, 90));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-14", "情况开始趋于稳定", "感觉好些了",
//                        160.2, 104.5, 96.4, 140.3, 165.3, 165, 162, 23, 56));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-15", "有所好转，建议注意康复过程的生活习惯", "感觉好些了",
//                        170.4, 102.4, 105.6, 170.2, 193, 176, 132, 54, 76));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-16", "已经好转许多", "感觉好些了",
//                        190, 105.3, 104.3, 120.5, 112, 198, 162, 65, 78));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-18", "已经痊愈", "感觉好些了",
//                        187.4, 106.3, 104.5, 133.2, 145, 189, 142, 78, 98));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-19", "已经痊愈", "感觉好些了",
//                        185, 143, 132, 135, 134, 143, 134, 67, 89));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-20", "已经痊愈", "感觉好些了",
//                        178, 156, 143, 145, 154, 154, 145, 65, 87));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-22", "已经痊愈", "感觉好些了",
//                        198, 123, 123, 123, 123, 156, 156, 86, 84));
//        KeeperPlusCache.getInstance().addTreatment(treatmentFollowupForPatientBean);
//
//
//        treatmentFollowupForPatientBean = new TreatmentBean("2015-11-06",
//                KeeperPlusCache.getInstance().getFriendByID("18081800000"),
//                KeeperPlusCache.getInstance().getCurrentUser(),
//                "因反复喘息多年，咳嗽、咯痰多年，心慌气急3年，复发并加重半天，在当地诊所予抗炎、止咳、扩张血管等治疗无好转来诊，体格检查： T 37.7℃，P 134次/分，R 27次/分，BP 112/72mmHg。口唇发绀，颈静脉怒张，胸廓呈桶状，颜面及双下肢水肿，心电图：右心室肥大，右束支传导阻滞。考虑慢性肺源性心脏病，建议住院治疗。");
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-09", "情况较差", "感觉好些了",
//                        190.4, 110, 87.4, 120.2, 142.2, 178.2, 122, 67, 90));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-10", "情况开始趋于稳定", "感觉好些了",
//                        160.2, 104.5, 96.4, 140.3, 165.3, 165, 162, 23, 56));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-11", "有所好转，建议注意康复过程的生活习惯", "感觉好些了",
//                        170.4, 102.4, 105.6, 170.2, 193, 176, 132, 54, 76));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-13", "已经好转许多", "感觉好些了",
//                        190, 105.3, 104.3, 120.5, 112, 198, 162, 65, 78));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-15", "已经痊愈", "感觉好些了",
//                        187.4, 106.3, 104.5, 133.2, 145, 189, 142, 78, 98));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-17", "已经痊愈", "感觉好些了",
//                        185, 143, 132, 135, 134, 143, 134, 67, 89));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-18", "已经痊愈", "感觉好些了",
//                        178, 156, 143, 145, 154, 154, 145, 65, 87));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-19", "已经痊愈", "感觉好些了",
//                        198, 123, 123, 123, 123, 156, 156, 86, 84));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-20", "已经痊愈", "感觉好些了",
//                        178, 156, 143, 145, 154, 154, 145, 65, 87));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-21", "已经痊愈", "感觉好些了",
//                        198, 123, 123, 123, 123, 156, 156, 86, 84));
//        KeeperPlusCache.getInstance().addTreatment(treatmentFollowupForPatientBean);
//
//
//        treatmentFollowupForPatientBean = new TreatmentBean("2015-11-08",
//                KeeperPlusCache.getInstance().getFriendByID("18081800001"),
//                KeeperPlusCache.getInstance().getCurrentUser(),
//                "反复喘促4年，双下肢水肿2年，腹胀1年，加重2天来诊，查见：P:111次/分，R:24次/分。端坐呼吸，二尖瓣面容。口唇紫绀，桶状胸，叩诊呈过清音，双肺呼吸音粗糙。心率125次/分，律不齐，心音强弱不等，二尖瓣膜听诊区可闻及隆隆样杂音。腹部膨隆，右侧肝区压痛明显。心电图：心房纤颤；轻度的ST-T异常。考虑风湿性心脏病，心房纤颤，二尖瓣关闭不全 ，心功能Ⅳ级。收住心内科治疗。");
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-09", "情况较差", "感觉好些了",
//                        190.4, 110, 87.4, 120.2, 142.2, 178.2, 122, 67, 90));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-10", "情况开始趋于稳定", "感觉好些了",
//                        160.2, 104.5, 96.4, 140.3, 165.3, 165, 162, 23, 56));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-11", "有所好转，建议注意康复过程的生活习惯", "感觉好些了",
//                        170.4, 102.4, 105.6, 170.2, 193, 176, 132, 54, 76));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-13", "已经好转许多", "感觉好些了",
//                        190, 105.3, 104.3, 120.5, 112, 198, 162, 65, 78));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-14", "已经痊愈", "感觉好些了",
//                        187.4, 106.3, 104.5, 133.2, 145, 189, 142, 78, 98));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-15", "已经痊愈", "感觉好些了",
//                        185, 143, 132, 135, 134, 143, 134, 67, 89));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-17", "已经痊愈", "感觉好些了",
//                        178, 156, 143, 145, 154, 154, 145, 65, 87));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-18", "已经痊愈", "感觉好些了",
//                        198, 123, 123, 123, 123, 156, 156, 86, 84));
//        treatmentFollowupForPatientBean.addTreatmentFollowup(
//                new TreatmentFollowup("2015-11-19", "已经痊愈", "感觉好些了",
//                        178, 156, 143, 145, 154, 154, 145, 65, 87));
//        KeeperPlusCache.getInstance().addTreatment(treatmentFollowupForPatientBean);
//    }
}
