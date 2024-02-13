<script setup lang="ts">
import { useRoute } from 'vue-router'
import { onMounted, inject, ref } from 'vue'
import DraftDetailComponent from '../components/DraftDetail/DraftDetailComponent.vue'
import DraftDetailCommunityThumbnailListComponent from '../components/DraftDetail/DraftDetailCommunityThumbnailListComponent.vue'
import PaletteDetailComponent from '../components/Palette/PaletteDetailComponent.vue'
import { localAxios } from '../api/axios'
const route = useRoute()
const draftId = route.params.id
const draft = ref<any>({
  draftId: 1,
  userId: 1,
  draftPrice: 100,
  draftColorCode: '{"color1": "#FF5733", "color2": "#C70039"}',
  draftThumbnail: 'thumbnail_1.jpg',
  draftFileLink: 'link_to_file_1',
  draftIsAI: true,
  draftIsPublic: true,
  draftIsDefault: false,
  draftIsDeleted: false,
  draftDownload: 5,
  draftBookmark: 10,
  draftPrompt: 'Sample prompt 1',
  draftTimestamp: '2024-01-29T03:00:00.000+00:00',
  categoryContents: []
})

const im = ref<any>({ order: '-1', imageUrl: '' })
console.log('hi')
console.log(im.value)

console.log(route.params)
onMounted(() => {
  localAxios()
    .get('draft/' + draftId)
    .then((res) => {
      draft.value = res.data
      console.log(draft.value)
    })
})
</script>

<template>
  <div
    style="
      display: flex;
      justify-content: center;
      align-items: center;
      bottom: 2rem;
      flex-direction: column;
    "
  >
    <DraftDetailComponent
      :draft-id="Number(draftId)"
      :image-thumbnail="draft.draftThumbnail"
      :draft-color-code="draft.draftColorCode"
    />
    <DraftDetailCommunityThumbnailListComponent :draft-id="Number(draftId)" />
  </div>
</template>

<style scoped></style>
