import {ref, computed} from 'vue'
import {defineStore} from 'pinia'

defineStore('counter', () => {
    const count = ref(0)
    const doubleCount = computed(() => count.value * 2)
    return {count, doubleCount}
});
