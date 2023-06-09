var admobid = {}
if (/(android)/i.test(navigator.userAgent)) {  // for android & amazon-fireos
  admobid = {
    banner: 'ca-app-pub-6827901881006590/7447792677',
    interstitial: 'ca-app-pub-6827901881006590/6330618713',
  }
} else if (/(ipod|iphone|ipad)/i.test(navigator.userAgent)) {  // for ios
  admobid = {
    banner: 'ca-app-pub-6827901881006590/7447792677',
    interstitial: 'ca-app-pub-6827901881006590/6330618713',
  }
}

document.addEventListener('deviceready', function() {
  admob.banner.config({
    id: admobid.banner,
    isTesting: true,
    autoShow: true,
  })
  admob.banner.prepare()

  admob.interstitial.config({
    id: admobid.interstitial,
    isTesting: true,
    autoShow: false,
  })
  admob.interstitial.prepare()

  document.getElementById('showAd').disabled = true
  document.getElementById('showAd').onclick = function() {
    admob.interstitial.show()
  }

}, false)

document.addEventListener('admob.banner.events.LOAD_FAIL', function(event) {
  console.log(event)
})

document.addEventListener('admob.interstitial.events.LOAD_FAIL', function(event) {
  console.log(event)
})

document.addEventListener('admob.interstitial.events.LOAD', function(event) {
  console.log(event)
  document.getElementById('showAd').disabled = false
})

document.addEventListener('admob.interstitial.events.CLOSE', function(event) {
  console.log(event)

  admob.interstitial.prepare()
})

