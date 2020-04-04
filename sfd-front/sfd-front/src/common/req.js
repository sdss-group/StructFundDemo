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
//根据registerCode查询fundCode列表
export function queryfundList (data) {

  return axios({
    url: 'http://' + Config.ip + ':' + Config.port + '/regAndFund/getFundList/'+data,
    method: 'get',
  })
}

export default {
  queryAllAgencyAndProcode
}
