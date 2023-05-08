<script setup>
</script>

<template>
    <main>
        <RouterLink to="/login">login</RouterLink>
        <button class="test" @click.prevent="test1">test</button>

    </main>
</template>

<script>
import {login, test} from "@/api/login";
import router from "@/router";

export default {
    data() {
        return {
            username: '',
            password: ''
        };
    },
    methods: {
        test1: function () {
            test().then(response=>{
                console.log(response)
            }).catch(error => {
                console.error(error);
            })

        }
    }
};
</script>

<style scoped>

</style>
