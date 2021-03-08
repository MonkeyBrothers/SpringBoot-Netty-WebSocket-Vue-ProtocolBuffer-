const nextControl = new Super.NextControl() // 实例化“下一个”按钮控件
const Dbspeen = new Super.DbspeenControl() // 倍速控件
const BarrageControl = new Super.BarrageControl() // 弹幕控件
const fullScreenControl = new Super.FullScreenControl() // 实例化“全屏”控件
const video = new Super.Svideo('videoContainer', {
    source: new Super.VideoSource({ // 引入视频资源
        src: 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Winston_Recall.mp4'
    }),
    leftControls: [nextControl], // 控件栏左槽插入控件
    rightControls: [Dbspeen, fullScreenControl], // 控件栏右槽插入控件
    centerControls: [BarrageControl] // 弹幕控件插入中间插槽
})
video.addEventListener('change', (event) => { // 监听video各属性变化
    //  console.log(event)
})
nextControl.addEventListener('click', () => { // 监听“下一个”按钮控件点击事件
    alert('click next menu !!!')
})
fullScreenControl.addEventListener('fullscreen', () => { // 监听进入全屏
    console.log('is fullscreen !!!')
})
fullScreenControl.addEventListener('cancelfullscreen', () => { // 监听退出全屏
    console.log('cancel fullscreen !!!')
})
video.addEventListener('fullscreen', () => {
    console.log('is fullscreen !!!')
})
video.addBarrage(new Super.Barrage('冲！冲！冲！', {
    color: 'red'
}))

