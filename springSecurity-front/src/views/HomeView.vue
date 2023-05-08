<script setup>
</script>

<template>
    <main>
        <RouterLink to="/login">login</RouterLink>
        <button class="test" @click.prevent="test1">test</button>
    </main>
</template>

<script>
import {test} from "@/api/login";

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
