<script setup lang = "ts">

const props = defineProps({
  // communityId : Number,
  src: String,
  heartFilled: Boolean,
  
});
console.log(props.heartFilled);


// const heart_filled = ref(props.heartFilled);
</script>

<template>
  <div class="communitycomponent" >
    <img :src="props.src" alt="">
    <div style="position: absolute; background: black; opacity: 50%; inset: 0; width: 100%; height: 100%;"></div>
    <div style = "position: absolute; width: 100%; height: 100%; display: flex; flex-direction: column; top: 0%;">
      <template v-if="props.heartFilled">
          <img src="@/assets/icon/heart_filled.png" alt="">
      </template>
      <template v-if="!props.heartFilled">
          <img src="@/assets/icon/heart_unfilled.png" alt="">
      </template>
    </div>
  </div>
</template>

<style scoped>
.communitycomponent {
  width: 10rem;
  height: 13rem;
  flex-shrink: 0;
  margin: 1px;
  position: relative; 
  margin:0.2rem
}

img {
  width: 100%;
  height: 100%;
  border-radius: 10%;
  object-fit: cover;
}
</style>
