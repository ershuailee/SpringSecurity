<template>
  <div>
    <nav class="navbar">
      <div class="navbar-container">
        <div class="logo">
          <a href="#">Logo</a>
        </div>
        <ul class="menu-items">
          <li v-for="(item, index) in menuItems" :key="index">
            <a :href="item.url">{{ item.text }}</a>
          </li>
        </ul>
      </div>
    </nav>
    <div class="content">
      <!-- 页面主体内容 -->
    </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            menuItems: [
                {text: "首页", url: "#"},
                {text: "关于我们", url: "#"},
                {text: "产品", url: "#"},
                {text: "联系我们", url: "#"},
            ],
        };
    },
};
</script>

<style scoped>
.navbar {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    //background-color: rgba(255, 255, 255, 0.8); /* 添加透明度 */
    z-index: 999;
}

.navbar-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
}

.logo a {
    font-size: 24px;
    font-weight: bold;
    text-decoration: none;
    color: #333;
}

.menu-items {
    display: flex;
    list-style: none;
    margin: 0;
    padding: 0;
}

li {
    margin-left: 20px;
}

a {
    text-decoration: none;
    color: #333;
    font-weight: bold;
}

.content {
    padding-top: 80px; /* 留出导航栏高度 */
}
</style>
