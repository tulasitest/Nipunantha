package com.globalsoft.nipunatha.bean;

import java.util.List;

public class citiesbean {

    /**
     * status : valid
     * message : records found
     * response : [{"id":"5","name":"Addanki","status":"1"},{"id":"6","name":"Adivivaram","status":"1"},{"id":"7","name":"Adoni","status":"1"},{"id":"8","name":"Aganampudi","status":"1"},{"id":"9","name":"Ajjaram","status":"1"},{"id":"10","name":"Akividu","status":"1"},{"id":"11","name":"Akkarampalle","status":"1"},{"id":"12","name":"Akkayapalle","status":"1"},{"id":"13","name":"Akkireddipalem","status":"1"},{"id":"14","name":"Alampur","status":"1"},{"id":"15","name":"Amalapuram","status":"1"},{"id":"16","name":"Amudalavalasa","status":"1"},{"id":"17","name":"Amur","status":"1"},{"id":"18","name":"Anakapalle","status":"1"},{"id":"19","name":"Anantapur","status":"1"},{"id":"20","name":"Andole","status":"1"},{"id":"21","name":"Atmakur","status":"1"},{"id":"22","name":"Attili","status":"1"},{"id":"23","name":"Avanigadda","status":"1"},{"id":"24","name":"Badepalli","status":"1"},{"id":"25","name":"Badvel","status":"1"},{"id":"26","name":"Balapur","status":"1"},{"id":"27","name":"Bandarulanka","status":"1"},{"id":"28","name":"Banganapalle","status":"1"},{"id":"29","name":"Bapatla","status":"1"},{"id":"30","name":"Bapulapadu","status":"1"},{"id":"31","name":"Belampalli","status":"1"},{"id":"32","name":"Bestavaripeta","status":"1"},{"id":"33","name":"Betamcherla","status":"1"},{"id":"34","name":"Bhattiprolu","status":"1"},{"id":"35","name":"Bhimavaram","status":"1"},{"id":"36","name":"Bhimunipatnam","status":"1"},{"id":"37","name":"Bobbili","status":"1"},{"id":"38","name":"Bombuflat","status":"1"},{"id":"39","name":"Bommuru","status":"1"},{"id":"40","name":"Bugganipalle","status":"1"},{"id":"41","name":"Challapalle","status":"1"},{"id":"42","name":"Chandur","status":"1"},{"id":"43","name":"Chatakonda","status":"1"},{"id":"44","name":"Chemmumiahpet","status":"1"},{"id":"45","name":"Chidiga","status":"1"},{"id":"46","name":"Chilakaluripet","status":"1"},{"id":"47","name":"Chimakurthy","status":"1"},{"id":"48","name":"Chinagadila","status":"1"},{"id":"49","name":"Chinagantyada","status":"1"},{"id":"50","name":"Chinnachawk","status":"1"},{"id":"51","name":"Chintalavalasa","status":"1"},{"id":"52","name":"Chipurupalle","status":"1"},{"id":"53","name":"Chirala","status":"1"},{"id":"54","name":"Chittoor","status":"1"},{"id":"55","name":"Chodavaram","status":"1"},{"id":"56","name":"Choutuppal","status":"1"},{"id":"57","name":"Chunchupalle","status":"1"},{"id":"58","name":"Cuddapah","status":"1"},{"id":"59","name":"Cumbum","status":"1"},{"id":"60","name":"Darnakal","status":"1"},{"id":"61","name":"Dasnapur","status":"1"},{"id":"62","name":"Dauleshwaram","status":"1"},{"id":"63","name":"Dharmavaram","status":"1"},{"id":"64","name":"Dhone","status":"1"},{"id":"65","name":"Dommara Nandyal","status":"1"},{"id":"66","name":"Dowlaiswaram","status":"1"},{"id":"67","name":"East Godavari Dist.","status":"1"},{"id":"68","name":"Eddumailaram","status":"1"},{"id":"69","name":"Edulapuram","status":"1"},{"id":"70","name":"Ekambara kuppam","status":"1"},{"id":"71","name":"Eluru","status":"1"},{"id":"72","name":"Enikapadu","status":"1"},{"id":"73","name":"Fakirtakya","status":"1"},{"id":"74","name":"Farrukhnagar","status":"1"},{"id":"75","name":"Gaddiannaram","status":"1"},{"id":"76","name":"Gajapathinagaram","status":"1"},{"id":"77","name":"Gajularega","status":"1"},{"id":"78","name":"Gajuvaka","status":"1"},{"id":"79","name":"Gannavaram","status":"1"},{"id":"80","name":"Garacharma","status":"1"},{"id":"81","name":"Garimellapadu","status":"1"},{"id":"82","name":"Giddalur","status":"1"},{"id":"83","name":"Godavarikhani","status":"1"},{"id":"84","name":"Gopalapatnam","status":"1"},{"id":"85","name":"Gopalur","status":"1"},{"id":"86","name":"Gorrekunta","status":"1"},{"id":"87","name":"Gudivada","status":"1"},{"id":"88","name":"Gudur","status":"1"},{"id":"89","name":"Guntakal","status":"1"},{"id":"90","name":"Guntur","status":"1"},{"id":"91","name":"Guti","status":"1"},{"id":"92","name":"Hindupur","status":"1"},{"id":"93","name":"Hukumpeta","status":"1"},{"id":"94","name":"Ichchapuram","status":"1"},{"id":"95","name":"Isnapur","status":"1"},{"id":"96","name":"Jaggayyapeta","status":"1"},{"id":"97","name":"Jallaram Kamanpur","status":"1"},{"id":"98","name":"Jammalamadugu","status":"1"},{"id":"99","name":"Jangampalli","status":"1"},{"id":"100","name":"Jarjapupeta","status":"1"},{"id":"101","name":"Kadiri","status":"1"},{"id":"102","name":"Kaikalur","status":"1"},{"id":"103","name":"Kakinada","status":"1"},{"id":"104","name":"Kallur","status":"1"},{"id":"105","name":"Kalyandurg","status":"1"},{"id":"106","name":"Kamalapuram","status":"1"},{"id":"107","name":"Kamareddi","status":"1"},{"id":"108","name":"Kanapaka","status":"1"},{"id":"109","name":"Kanigiri","status":"1"},{"id":"110","name":"Kanithi","status":"1"},{"id":"111","name":"Kankipadu","status":"1"},{"id":"112","name":"Kantabamsuguda","status":"1"},{"id":"113","name":"Kanuru","status":"1"},{"id":"114","name":"Karnul","status":"1"},{"id":"115","name":"Katheru","status":"1"},{"id":"116","name":"Kavali","status":"1"},{"id":"117","name":"Kazipet","status":"1"},{"id":"118","name":"Khanapuram Haveli","status":"1"},{"id":"119","name":"Kodar","status":"1"},{"id":"120","name":"Kollapur","status":"1"},{"id":"121","name":"Kondapalem","status":"1"},{"id":"122","name":"Kondapalle","status":"1"},{"id":"123","name":"Kondukur","status":"1"},{"id":"124","name":"Kosgi","status":"1"},{"id":"125","name":"Kothavalasa","status":"1"},{"id":"126","name":"Kottapalli","status":"1"},{"id":"127","name":"Kovur","status":"1"},{"id":"128","name":"Kovurpalle","status":"1"},{"id":"129","name":"Kovvur","status":"1"},{"id":"130","name":"Krishna","status":"1"},{"id":"131","name":"Kuppam","status":"1"},{"id":"132","name":"Kurmannapalem","status":"1"},{"id":"133","name":"Kurnool","status":"1"},{"id":"134","name":"Lakshettipet","status":"1"},{"id":"135","name":"Lalbahadur Nagar","status":"1"},{"id":"136","name":"Machavaram","status":"1"},{"id":"137","name":"Macherla","status":"1"},{"id":"138","name":"Machilipatnam","status":"1"},{"id":"139","name":"Madanapalle","status":"1"},{"id":"140","name":"Madaram","status":"1"},{"id":"141","name":"Madhuravada","status":"1"},{"id":"142","name":"Madikonda","status":"1"},{"id":"143","name":"Madugule","status":"1"},{"id":"144","name":"Mahabubnagar","status":"1"},{"id":"145","name":"Mahbubabad","status":"1"},{"id":"146","name":"Malkajgiri","status":"1"},{"id":"147","name":"Mamilapalle","status":"1"},{"id":"148","name":"Mancheral","status":"1"},{"id":"149","name":"Mandapeta","status":"1"},{"id":"150","name":"Mandasa","status":"1"},{"id":"151","name":"Mangalagiri","status":"1"},{"id":"152","name":"Manthani","status":"1"},{"id":"153","name":"Markapur","status":"1"},{"id":"154","name":"Marturu","status":"1"},{"id":"155","name":"Metpalli","status":"1"},{"id":"156","name":"Mindi","status":"1"},{"id":"157","name":"Mirpet","status":"1"},{"id":"158","name":"Moragudi","status":"1"},{"id":"159","name":"Mothugudam","status":"1"},{"id":"160","name":"Nagari","status":"1"},{"id":"161","name":"Nagireddipalle","status":"1"},{"id":"162","name":"Nandigama","status":"1"},{"id":"163","name":"Nandikotkur","status":"1"},{"id":"164","name":"Nandyal","status":"1"},{"id":"165","name":"Narasannapeta","status":"1"},{"id":"166","name":"Narasapur","status":"1"},{"id":"167","name":"Narasaraopet","status":"1"},{"id":"168","name":"Narayanavanam","status":"1"},{"id":"169","name":"Narsapur","status":"1"},{"id":"170","name":"Narsingi","status":"1"},{"id":"171","name":"Narsipatnam","status":"1"},{"id":"172","name":"Naspur","status":"1"},{"id":"173","name":"Nathayyapalem","status":"1"},{"id":"174","name":"Nayudupeta","status":"1"},{"id":"175","name":"Nelimaria","status":"1"},{"id":"176","name":"Nellore","status":"1"},{"id":"177","name":"Nidadavole","status":"1"},{"id":"178","name":"Nuzvid","status":"1"},{"id":"179","name":"Omerkhan daira","status":"1"},{"id":"180","name":"Ongole","status":"1"},{"id":"181","name":"Osmania University","status":"1"},{"id":"182","name":"Pakala","status":"1"},{"id":"183","name":"Palakole","status":"1"},{"id":"184","name":"Palakurthi","status":"1"},{"id":"185","name":"Palasa","status":"1"},{"id":"186","name":"Palempalle","status":"1"},{"id":"187","name":"Palkonda","status":"1"},{"id":"188","name":"Palmaner","status":"1"},{"id":"189","name":"Pamur","status":"1"},{"id":"190","name":"Panjim","status":"1"},{"id":"191","name":"Papampeta","status":"1"},{"id":"192","name":"Parasamba","status":"1"},{"id":"193","name":"Parvatipuram","status":"1"},{"id":"194","name":"Patancheru","status":"1"},{"id":"195","name":"Payakaraopet","status":"1"},{"id":"196","name":"Pedagantyada","status":"1"},{"id":"197","name":"Pedana","status":"1"},{"id":"198","name":"Peddapuram","status":"1"},{"id":"199","name":"Pendurthi","status":"1"},{"id":"200","name":"Penugonda","status":"1"},{"id":"201","name":"Penukonda","status":"1"},{"id":"202","name":"Phirangipuram","status":"1"},{"id":"203","name":"Pithapuram","status":"1"},{"id":"204","name":"Ponnur","status":"1"},{"id":"205","name":"Port Blair","status":"1"},{"id":"206","name":"Pothinamallayyapalem","status":"1"},{"id":"207","name":"Prakasam","status":"1"},{"id":"208","name":"Prasadampadu","status":"1"},{"id":"209","name":"Prasantinilayam","status":"1"},{"id":"210","name":"Proddatur","status":"1"},{"id":"211","name":"Pulivendla","status":"1"},{"id":"212","name":"Punganuru","status":"1"},{"id":"213","name":"Puttur","status":"1"},{"id":"214","name":"Qutubullapur","status":"1"},{"id":"215","name":"Rajahmundry","status":"1"},{"id":"216","name":"Rajamahendri","status":"1"},{"id":"217","name":"Rajampet","status":"1"},{"id":"218","name":"Rajendranagar","status":"1"},{"id":"219","name":"Rajoli","status":"1"},{"id":"220","name":"Ramachandrapuram","status":"1"},{"id":"221","name":"Ramanayyapeta","status":"1"},{"id":"222","name":"Ramapuram","status":"1"},{"id":"223","name":"Ramarajupalli","status":"1"},{"id":"224","name":"Ramavarappadu","status":"1"},{"id":"225","name":"Rameswaram","status":"1"},{"id":"226","name":"Rampachodavaram","status":"1"},{"id":"227","name":"Ravulapalam","status":"1"},{"id":"228","name":"Rayachoti","status":"1"},{"id":"229","name":"Rayadrug","status":"1"},{"id":"230","name":"Razam","status":"1"},{"id":"231","name":"Razole","status":"1"},{"id":"232","name":"Renigunta","status":"1"},{"id":"233","name":"Repalle","status":"1"},{"id":"234","name":"Rishikonda","status":"1"},{"id":"235","name":"Salur","status":"1"},{"id":"236","name":"Samalkot","status":"1"},{"id":"237","name":"Sattenapalle","status":"1"},{"id":"238","name":"Seetharampuram","status":"1"},{"id":"239","name":"Serilungampalle","status":"1"},{"id":"240","name":"Shankarampet","status":"1"},{"id":"241","name":"Shar","status":"1"},{"id":"242","name":"Singarayakonda","status":"1"},{"id":"243","name":"Sirpur","status":"1"},{"id":"244","name":"Sirsilla","status":"1"},{"id":"245","name":"Sompeta","status":"1"},{"id":"246","name":"Sriharikota","status":"1"},{"id":"247","name":"Srikakulam","status":"1"},{"id":"248","name":"Srikalahasti","status":"1"},{"id":"249","name":"Sriramnagar","status":"1"},{"id":"250","name":"Sriramsagar","status":"1"},{"id":"251","name":"Srisailam","status":"1"},{"id":"252","name":"Srisailamgudem Devasthanam","status":"1"},{"id":"253","name":"Sulurpeta","status":"1"},{"id":"254","name":"Suriapet","status":"1"},{"id":"255","name":"Suryaraopet","status":"1"},{"id":"256","name":"Tadepalle","status":"1"},{"id":"257","name":"Tadepalligudem","status":"1"},{"id":"258","name":"Tadpatri","status":"1"},{"id":"259","name":"Tallapalle","status":"1"},{"id":"260","name":"Tanuku","status":"1"},{"id":"261","name":"Tekkali","status":"1"},{"id":"262","name":"Tenali","status":"1"},{"id":"263","name":"Tigalapahad","status":"1"},{"id":"264","name":"Tiruchanur","status":"1"},{"id":"265","name":"Tirumala","status":"1"},{"id":"266","name":"Tirupati","status":"1"},{"id":"267","name":"Tirvuru","status":"1"},{"id":"268","name":"Trimulgherry","status":"1"},{"id":"269","name":"Tuni","status":"1"},{"id":"270","name":"Turangi","status":"1"},{"id":"271","name":"Ukkayapalli","status":"1"},{"id":"272","name":"Ukkunagaram","status":"1"},{"id":"273","name":"Uppal Kalan","status":"1"},{"id":"274","name":"Upper Sileru","status":"1"},{"id":"275","name":"Uravakonda","status":"1"},{"id":"276","name":"Vadlapudi","status":"1"},{"id":"277","name":"Vaparala","status":"1"},{"id":"278","name":"Vemalwada","status":"1"},{"id":"279","name":"Venkatagiri","status":"1"},{"id":"280","name":"Venkatapuram","status":"1"},{"id":"281","name":"Vepagunta","status":"1"},{"id":"282","name":"Vetapalem","status":"1"},{"id":"283","name":"Vijayapuri","status":"1"},{"id":"284","name":"Vijayapuri South","status":"1"},{"id":"285","name":"Vijayawada","status":"1"},{"id":"286","name":"Vinukonda","status":"1"},{"id":"287","name":"Visakhapatnam","status":"1"},{"id":"288","name":"Vizianagaram","status":"1"},{"id":"289","name":"Vuyyuru","status":"1"},{"id":"290","name":"Wanparti","status":"1"},{"id":"291","name":"West Godavari Dist.","status":"1"},{"id":"292","name":"Yadagirigutta","status":"1"},{"id":"293","name":"Yarada","status":"1"},{"id":"294","name":"Yellamanchili","status":"1"},{"id":"295","name":"Yemmiganur","status":"1"},{"id":"296","name":"Yenamalakudru","status":"1"},{"id":"297","name":"Yendada","status":"1"},{"id":"298","name":"Yerraguntla","status":"1"}]
     */

    private String status;
    private String message;
    private List<ResponseBean> response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseBean> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseBean> response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * id : 5
         * name : Addanki
         * status : 1
         */

        private String id;
        private String name;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
