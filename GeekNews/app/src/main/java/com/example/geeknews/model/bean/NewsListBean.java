package com.example.geeknews.model.bean;

import java.util.List;

/**
 * Created by 张嘉河 on 2019/4/10.
 */

public class NewsListBean {

    /**
     * date : 20190418
     * stories : [{"images":["https://pic1.zhimg.com/v2-aa9fb1b1a773f66136e55b26bd23098c.jpg"],"type":0,"id":9710348,"ga_prefix":"041809","title":"《鲁邦三世》原作者加藤一彦去世，它曾在中国热播"},{"images":["https://pic2.zhimg.com/v2-58b5abf70967054a9a057d4f1da8f811.jpg"],"type":0,"id":9710332,"ga_prefix":"041808","title":"死在直播镜头前，她度过了辛苦的一生"},{"title":"华为 P30 Pro 拍的月亮是 PS 的吗？","ga_prefix":"041807","images":["https://pic4.zhimg.com/v2-bb8c0fb5fb4bb6886d2b0e62901a4047.jpg"],"multipic":true,"type":0,"id":9710350},{"images":["https://pic4.zhimg.com/v2-8f86f8617be5f4770b922e5f0baa963f.jpg"],"type":0,"id":9710343,"ga_prefix":"041806","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic4.zhimg.com/v2-d9ebcf5d40d1563b93df0a9f9fdc5e17.jpg","type":0,"id":9710350,"ga_prefix":"041807","title":"华为 P30 Pro 拍的月亮是 PS 的吗？"},{"image":"https://pic1.zhimg.com/v2-e61227b2e6f420f6c62a71f089db8100.jpg","type":0,"id":9710289,"ga_prefix":"041707","title":"三星 Galaxy Fold：它是一台手机，但屏幕告诉你它不是"},{"image":"https://pic2.zhimg.com/v2-4b16fd1c9c22d5bc115d75100bcc536d.jpg","type":0,"id":9710280,"ga_prefix":"041709","title":"用游戏「实地」图解：巴黎圣母院为什么会着火？"},{"image":"https://pic3.zhimg.com/v2-26b7a11ab1f5ef8b4aadaf773069f48e.jpg","type":0,"id":9710258,"ga_prefix":"041607","title":"一个巨大的汽车行业潜规则，就这么不经意间捅了出来"},{"image":"https://pic1.zhimg.com/v2-18c6d20db38a2d8b0efc746f09803830.jpg","type":0,"id":9710248,"ga_prefix":"041521","title":"「权游」最终季第 1 集：最好最好的重逢，久违了"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-aa9fb1b1a773f66136e55b26bd23098c.jpg"]
         * type : 0
         * id : 9710348
         * ga_prefix : 041809
         * title : 《鲁邦三世》原作者加藤一彦去世，它曾在中国热播
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic4.zhimg.com/v2-d9ebcf5d40d1563b93df0a9f9fdc5e17.jpg
         * type : 0
         * id : 9710350
         * ga_prefix : 041807
         * title : 华为 P30 Pro 拍的月亮是 PS 的吗？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
