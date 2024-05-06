<script setup>
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
const locale = zhCn

</script>

<template>
  <!-- 设置语言为中文 -->
  <el-config-provider :locale="locale">
      <router-view></router-view>
   </el-config-provider>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}

#nprogress .bar {
  background: #409eff !important;
}
</style>
