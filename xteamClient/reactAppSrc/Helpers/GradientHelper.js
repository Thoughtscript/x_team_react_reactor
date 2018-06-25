'use strict'

/**
 *  Linear Gradient Helper.
 *
 *  @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

export const gradient = el => {
  const m = document.querySelectorAll(el)[0],

    gradients = [
      ['#ad5389', '-webkit-linear-gradient(to left, #ad5389, #3c1053)', 'linear-gradient(to left, #ad5389, #3c1053)'],
      ['#FC5C7D', '-webkit-linear-gradient(to right, #6A82FB, #FC5C7D)', 'linear-gradient(to right, #6A82FB, #FC5C7D)'],
      ['#800080', 'webkit-linear-gradient(to right, #800080, #ffc0cb)', 'linear-gradient(to right, #800080, #ffc0cb)'],
      ['#c0392b', '-webkit-linear-gradient(to right, #c0392b, #8e44ad)', 'linear-gradient(to right, #c0392b, #8e44ad)'],
      ['#d3959b', '-webkit-linear-gradient(to right, #d3959b, #bfe6ba)', 'linear-gradient(to right, #d3959b, #bfe6ba)'],
      ['#108dc7', '-webkit-linear-gradient(to right, #108dc7, #ef8e38)', 'linear-gradient(to right, #108dc7, #ef8e38)'],
      ['#333333', '-webkit-linear-gradient(to right, #dd1818, #333333)', 'linear-gradient(to right, #dd1818, #333333)'],
      ['#00b09b', '-webkit-linear-gradient(to right, #96c93d, #00b09b)', 'linear-gradient(to right, #96c93d, #00b09b)'],
      ['#1a2a6c', '-webkit-linear-gradient(to right, #fdbb2d, #b21f1f, #1a2a6c)', 'linear-gradient(to right, #fdbb2d, #b21f1f, #1a2a6c)']
    ]

  const innerFunction = (m) => {
    setTimeout(() => {
      //browser shim for gradient
      const gradient = gradients[Math.floor(Math.random() * Math.floor(gradients.length))];
      if (navigator.userAgent.indexOf("Chrome") || navigator.userAgent.indexOf("Safari")) m.style.background = gradient[1]
      if (navigator.userAgent.indexOf("Firefox")) m.style.background = gradient[2]
      else m.style.background = gradient[0]
      innerFunction(m)
    }, 10000)
  }

  innerFunction(m)

}
