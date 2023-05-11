<template>
  <ul class="message-list">
    <li
        v-for="(item, index) in messageList"
        :key="index"
        :class="['message-item', setClass(item.type)]"
    >
      {{ item.title }}
    </li>
  </ul>
</template>

<script setup lang="ts">
interface MessageItem {
    type: string;
    title: string;
}

defineProps({
    messageList: {
        type: Array as () => MessageItem[],
        default: [],
    },
});

function setClass(type: string): string {
    if (type === 'info') {
        return 'message-info-item';
    } else if (type === 'warning') {
        return 'message-warning-item';
    } else if (type === 'success') {
        return 'message-success-item';
    } else if (type === 'error') {
        return 'message-error-item';
    } else {
        return '';
    }
}
</script>

<style lang="less" scoped>
.message-list {
  position: fixed;

  left: 50%;
  transform: translate(-50%, -50%);
  top: 24px;
  right: 24px;
  z-index: 1000;
  max-width: 200px;
  margin-bottom: 0;
  text-align: right;

  .message-item {
    display: inline-block;
    padding: 12px 24px;
    border: 1px solid #b3d0cf;
    margin-left: 8px;
    margin-bottom: 12px;
    background-color: #e6f3ff;
    font-size: 14px;
    color: #007bff;
    text-align: left;
    box-shadow: 0 1px 1px 0 hsla(0, 0%, 80.4%, 0.5);
    border-radius: 2px;
    cursor: default;
  }

  .message-info-item {
    border: 1px solid #b3d0cf;
    background-color: #e6f3ff;
    color: #007bff;
  }

  .message-error-item {
    border: 1px solid #e99;
    background-color: #f6e3e3;
    color: #e33;
  }
}
</style>