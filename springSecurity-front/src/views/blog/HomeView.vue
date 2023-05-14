<template>
  <Item/>
    <div class="blog-home">
      <div class="hero-image">
        <h1>Welcome to My Blog</h1>
        <p>Here you can find my latest articles</p>
      </div>
      <div class="articles">
        <div class="article" v-for="(article, index) in articles" :key="index">
          <img :src="article.image" alt="">
          <div class="article-content">
            <h2>{{ article.title }}</h2>
            <p>{{ article.summary }}</p>
            <a :href="article.url">Read more</a>
          </div>
        </div>
      </div>
    </div>
</template>

<script setup lang="ts">

import Item from "@/components/Item.vue";
</script>

<!--<template>-->

<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--    data() {-->
<!--        return {-->
<!--            articles: [-->
<!--                {-->
<!--                    title: "My First Article",-->
<!--                    summary: "This is a summary of my first article",-->
<!--                    image: "https://via.placeholder.com/300x200",-->
<!--                    url: "#",-->
<!--                },-->
<!--                {-->
<!--                    title: "My Second Article",-->
<!--                    summary: "This is a summary of my second article",-->
<!--                    image: "https://via.placeholder.com/300x200",-->
<!--                    url: "#",-->
<!--                },-->
<!--                {-->
<!--                    title: "My Third Article",-->
<!--                    summary: "This is a summary of my third article",-->
<!--                    image: "https://via.placeholder.com/300x200",-->
<!--                    url: "#",-->
<!--                },-->
<!--            ],-->
<!--        };-->
<!--    },-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.hero-image {-->
<!--    height: 300px;-->
<!--    background-image: url("https://via.placeholder.com/1200x300");-->
<!--    background-repeat: no-repeat;-->
<!--    background-size: cover;-->
<!--    display: flex;-->
<!--    flex-direction: column;-->
<!--    justify-content: center;-->
<!--    align-items: center;-->
<!--    color: #fff;-->
<!--    text-align: center;-->
<!--}-->

<!--.article {-->
<!--    display: flex;-->
<!--    margin: 20px;-->
<!--}-->

<!--.article img {-->
<!--    width: 300px;-->
<!--    height: 200px;-->
<!--    object-fit: cover;-->
<!--    margin-right: 20px;-->
<!--}-->

<!--.article-content h2 {-->
<!--    margin-bottom: 10px;-->
<!--}-->

<!--.article-content p {-->
<!--    margin-bottom: 10px;-->
<!--    line-height: 1.5;-->
<!--}-->

<!--.article-content a {-->
<!--    color: #333;-->
<!--    text-decoration: none;-->
<!--    font-weight: bold;-->
<!--}-->
<!--</style>-->
