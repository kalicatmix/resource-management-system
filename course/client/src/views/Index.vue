<template>
<div>
    <router-view name="index"/>
    <router-view name="subhome"/>
    </div>
</template>

<script>
import home from "./Home.vue"
export default {
    components:{
        home
    }
}
</script>
