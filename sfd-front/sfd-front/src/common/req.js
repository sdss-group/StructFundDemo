import axios from 'axios'
import Config from '@/Config'
// 查询产品客户类型
function queryAllAgencyAndProcode () {
  return axios({
    url: 'http://' + Config.ip + ':' + Config.port + '/queryTrans/queryAllAgencyAndProcode ',
    method: 'get'
  })
}
//查询registerCode列表
export function queryRegList () {
  return axios({
    url: 'http://' + Config.ip + ':' + Config.port + '/regAndFund/getRegList ',
    method: 'get'
  })
}

export default {
  queryAllAgencyAndProcode
}
