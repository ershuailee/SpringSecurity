<template>
  <main>
    <RouterLink to="/login">login</RouterLink>
    <button @click="button">button</button>
  </main>
</template>

<script lang="ts">
import Message from "@/components/message";

export default {
    name: 'Counter',
    setup() {

        function button(): void {
            Message.info('详情跳转');
        }

        return {
            button,
        };
    },
};
</script>