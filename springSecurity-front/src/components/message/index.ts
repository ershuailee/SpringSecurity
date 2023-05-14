import type { Component, App } from 'vue';
import MButton from './MButton.vue';
import MInput from './MInput.vue';
import MCheckbox from './MCheckbox.vue';

const components: {
    [propName: string]: Component;
} = {
    MButton,
    MInput,
    MCheckbox
};

export default {
    install: (app: App) => {
        for (const key in components) {
            app.component(key, components[key]);
        }
    }
};