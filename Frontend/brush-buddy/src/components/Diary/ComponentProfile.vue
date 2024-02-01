<template>
<div id="wrapper">
    <div id="profile-info">
        <div id="profile-avartar">
            <img src="@/assets/logo.png" alt="프로필사진">
        </div>
        <div>
            <div id="name-box">
                <span id="name">김싸피</span>
                <span>님</span>
            </div>
            <div>다채로운 하루 되세요!</div>
        </div>
    </div>
    <div id="modify">프로필 수정</div>
    <div id="milage">
        <div>
            보유한 마일리지
        </div>
        <div id="amount">
            <div id="num">
                9500
            </div>
            <div id="M">
                M
            </div>
        </div>
    </div>
</div>
</template>
<script setup lang="ts">

</script>
<style scoped>
#wrapper {
    display: flex;
    flex-direction: column;
    padding: 2rem 2rem 1rem;
    background-color: #f9f0ff;
}

#profile-info {
    display: flex;
    justify-content: inline-flex;
    align-items: center;
}
#profile-avartar {
    border-radius: 50%;
    background-color: #ffffff;
    width: 4rem;
    height: 4rem;
    margin-right: 1rem;
    padding:1rem;
    overflow: hidden;
    > img {
        width:100%;
        height:100%;
        object-fit:cover;
    }
}
#name-box{
    vertical-align: sub;
    margin-bottom: 0.5rem;
    >span{
        vertical-align: sub;
    }
}
#name{
    font-size: 1.5rem;
    font-weight: 600;
    color: #ae67e4;
}

#modify {
    margin-left: auto;
    color: gray;
    font-size: small;
}
#milage {
    border-style: solid;
    border-width: thin;
    border-color: lightslategray;
    background-color: #ffffff;
    border-radius: 10px;
    padding: 0.5rem 1rem 0.5rem;
    margin-top: 0.5rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
#amount{
    font-weight: bold;
    justify-content: center;
    display: flex;
    align-items: center;
}
#num{
    font-size: 1.5rem;
    color: #ae67e4;
}
#M{
    padding-left: 0.5rem;
}
</style>