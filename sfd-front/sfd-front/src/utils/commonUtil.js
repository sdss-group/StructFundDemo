/**
 * 公共函数
 */
import base64js from 'base64-js'
import CryptoJS from 'crypto-js'

let userToken = ''
export function setUserToken (token) {
  userToken = token
}
export function clearToken () {
  userToken = ''
}
export function getUserToken (token) {
  let t = localStorage.getItem('userAuthToken')
  return userToken || (t || '')
  // return userToken;
}
export function setUserTokenToLocalStorage (userAuthToken, loginName, password, isFreeLoginName) {
  localStorage.setItem('userAuthToken', getUserToken(userAuthToken))
  localStorage.setItem('loginName', loginName)
  localStorage.setItem('password', password)
  localStorage.setItem('isFreeLoginName', isFreeLoginName)
}
export function clearUserDataFromLocalStorage () {
  localStorage.removeItem('userAuthToken')
  // if (loginName && password) {
  localStorage.removeItem('password')
  // }
}

/**
 * BASE64解码
 * @param {*} base64Str
 */
export function base64Decode (base64Str) {
  let byteArr = base64js.toByteArray(base64Str)
  return Buffer.alloc(byteArr).toString()
}
/**
 * BASE64转码
 * @param {*} base64Str
 */
export function base64Encode (str) {
  return base64js.fromByteArray(Buffer.alloc(str))
}

const AESKEY = CryptoJS.enc.Utf8.parse('4KE98FE9DKR9E3JS')
const AESIV = CryptoJS.enc.Utf8.parse('4KE98FE9DKR9E3JS')
export function AESEncode (str) {
  let encStr = CryptoJS.AES.encrypt(CryptoJS.enc.Utf8.parse(str), AESKEY, {
    iv: AESIV,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.ZeroPadding
  })
  return encStr.toString()
}

export function AESDecode (str) {
  let decStr = CryptoJS.AES.decrypt(str, AESKEY, {
    iv: AESIV,
    mode: CryptoJS.mode.CBC,
    padding: CryptoJS.pad.ZeroPadding
  })
  return CryptoJS.enc.Utf8.stringify(decStr)
}

// DES加密，在对结果进行BASE64转码
export function DESEncrypt (str, key) {
  var keyHex = CryptoJS.enc.Utf8.parse(key)
  let encStr = CryptoJS.DES.encrypt(CryptoJS.enc.Utf8.parse(str), keyHex, {
    mode: CryptoJS.mode.ECB
    // padding: CryptoJS.pad.Pkcs7,
  })
  return encStr.toString()
}
export function DESDecrypt (str, key) {
  var keyHex = CryptoJS.enc.Utf8.parse(key)
  let desStr = CryptoJS.DES.decrypt({ciphertext: CryptoJS.enc.Base64.parse(str.toString())}, keyHex, {
    mode: CryptoJS.mode.ECB
  })
  return desStr.toString(CryptoJS.enc.Utf8)
}

export function isLogin () {
  return userToken != ''
}

export function getDateDiff (dateTime) {
  var dateTimeStamp = Date.parse(new Date(dateTime))
  var minute = 1000 * 60
  var hour = minute * 60
  var day = hour * 24
  // var halfMonth = day * 15;
  var month = day * 30
  var now = new Date().getTime()
  var diffValue = now - dateTimeStamp
  if (diffValue < 0) {
    return
  }
  var monthC = diffValue / month
  var weekC = diffValue / (7 * day)
  var dayC = diffValue / day
  var hourC = diffValue / hour
  var minC = diffValue / minute
  var result
  if (monthC >= 1) {
    result = '' + parseInt(monthC, 10) + '月前'
  } else if (weekC >= 1) {
    result = '' + parseInt(weekC, 10) + '周前'
  } else if (dayC >= 1) {
    result = '' + parseInt(dayC, 10) + '天前'
  } else if (hourC >= 1) {
    result = '' + parseInt(hourC, 10) + '小时前'
  } else if (minC >= 1) {
    result = '' + parseInt(minC, 10) + '分钟前'
  } else {
    result = '刚刚'
  }
  return result
}

/**
 * 下载产品说明书
 * @param {*OBJECT} params
 * @param {*FUNCTION} successCallback
 * @param {*FUNCTION} failCallback
 */
export function downloadProductMannual (params, url, successCallback) {
  fetch(url, {
    method: 'POST',
    headers: {
      'Accept': 'application/json;charset=utf-8',
      'Accept-Language': 'zh-CN',
      'Content-Type': 'application/x-www-form-urlencoded;'
    },
    body: encodeURI(`REQ_MESSAGE=${JSON.stringify(getMessage(params))}`),
    mode: 'cors',
    cache: 'no-store'
  }).then((response) => {
    response.blob().then((blob) => {
      successCallback(blob)
    })
  })
}

function getMessageHead () {
  return {
    'TRANS_PROCESS': '',
    'TRAN_ID': ''
  }
}

function getMessage (data) {
  return {
    'REQ_HEAD': getMessageHead(),
    'REQ_BODY': data
  }
}

/* eslint no-useless-escape:0 */
const reg = /(((^https?:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)$/g

export function isUrl (path) {
  return reg.test(path)
}

// list转换Map方法
export function objToMap (obj) {
  let strMap = new Map()
  for (let k of Object.keys(obj)) {
    strMap.set(obj[k].value, obj[k].text)
  }
  return strMap
}

// Array转换Map方法
export function arrToMap (obj) {
  let strMap = new Map()
  for (let k of Object.keys(obj)) {
    strMap.set(obj[k].value, obj[k].label)
  }
  return strMap
}

// Array转换Map方法Key和value
export function arrToMapByKV (obj) {
  let strMap = new Map()
  for (let k of Object.keys(obj)) {
    strMap.set(obj[k].key, obj[k].value)
  }
  return strMap
}

export function numToSan (obj) {
  if (obj == null || obj == '') {
    return obj
  } else {
    let s = obj.toString().split('.')
    let sInt = s[0]
    let sFloat = s[1]
    let sInt1 = sInt.replace(/\d(?=(\d{3})+$)/g, '$&,')
    return sFloat ? sInt1 + '.' + sFloat : sInt1
  }
}

export function dateFormat (row, column) {
  var date = row[column.property]
  if (date == undefined) { return '' };
  return this.$moment(date).format('YYYY-MM-DD')
}

