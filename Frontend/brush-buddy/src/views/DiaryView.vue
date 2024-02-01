<template>
    <div>
        <div>
            <CProfile />
        </div>
    <div class="sticky">
        <CMenuTab />
    </div>
        <CCard/>
    </div>
</template>

<script setup lang="ts">
import CProfile from "../components/Diary/CProfile.vue"
import CMenuTab from "../components/Diary/CMenuTab.vue"
import CCard from "../components/Diary/CCard.vue"
</script>

<style scoped>

    .sticky{
        position:sticky;
        top:0px;
        line-height: 4rem;
        background-color: #ffffff;
    }

</style>