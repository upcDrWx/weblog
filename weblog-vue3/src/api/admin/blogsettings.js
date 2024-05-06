import axios from "@/axios";

// 获取博客设置详情
export function getBlogSettingsDetail() {
    return axios.post("/admin/blog/settings/detail")
}

export function updateBlogSettings(data) {
    return axios.post("/admin/blog/settings/update", data)
}