<template>
  <div class="message-list">
    <div v-for="(item, index) in messageList" :key="index" class="message-item" :class="setClass(item.type)">
      <span>{{ item.title }}</span>
    </div>
  </div>
</template>

<script setup lang="ts">
import {defineProps} from 'vue';

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
  transform: translateX(-50%);
  top: 24px;
  right: 24px;
  z-index: 1000;
  width: 300px;
  margin-bottom: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .message-item {
    position: relative;
    display: inline-block;
    padding: 12px 24px;
    background-color: #2020ce;
    width: 300px;
    font-size: 16px;
    color: #303133;
    text-align: center;
    border-radius: 6px;
    margin-bottom: 10px;
  }

  .message-info-item {
    border-color: #909399;
    background-color: #ecf5ff;
    color: #409EFF;
  }

  .message-success-item {
    border-color: #e2eaf1;
    background-color: #f0f9eb;
    color: #67C23A;
  }

  .message-warning-item {
    border-color: #f7d154;
    background-color: #fdf6ec;
    color: #E6A23C;
  }

  .message-error-item {
    border-color: #f56c6c;
    background-color: #fef0f0;
    color: #F56C6C;
  }

  .close-btn {
    position: absolute;
    top: 0;
    right: 0;
    padding: 4px;
    margin: 2px;
    font-size: 12px;
    color: #ccc;
    background: none;
    border: none;
    cursor: pointer;
  }

  .close-btn:hover {
    color: #fff;
  }
}
</style>
