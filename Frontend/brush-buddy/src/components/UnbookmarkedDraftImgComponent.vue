<template>
    <div id = "draft">
        <div id = "bookmark">
         <svg xmlns="http://www.w3.org/2000/svg" width="25" height="31" viewBox="0 0 25 31" fill="none">
            <g filter="url(#filter0_d_492_1949)">
                <path d="M5 0V21.9688L12.6298 15.5612L19.7333 21.9688V0H5Z" fill="white"/>
                <path d="M5 0V21.9688L12.6298 15.5612L19.7333 21.9688V0H5Z" stroke="#E2E2E2" stroke-linecap="round" stroke-linejoin="round"/>
            </g>
            <defs>
                <filter id="filter0_d_492_1949" x="0.5" y="-0.5" width="23.7333" height="30.9688" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
                    <feFlood flood-opacity="0" result="BackgroundImageFix"/>
                    <feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha"/>
                    <feOffset dy="4"/>
                    <feGaussianBlur stdDeviation="2"/>
                    <feComposite in2="hardAlpha" operator="out"/>
                    <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.6 0"/>
                    <feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow_492_1949"/>
                    <feBlend mode="normal" in="SourceGraphic" in2="effect1_dropShadow_492_1949" result="shape"/>
                </filter>
            </defs>
        </svg>
        </div>
        <img src="@/assets/images/draft_img.png" alt="draft" width="330" height="330"  />     
    </div>
</template>

<script setup lang="ts">
   
</script>

<style scoped>
    #bookmark {
        position: absolute;
        top: 0;
        right: 1rem;
    }

    #draft {
        position: absolute;
        background: #FFFFFF;
        box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.25);
        border-radius: 20px;

    }
</style>