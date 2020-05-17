import axios from 'axios'
import Config from '@/Config'

// 查询registerCode列表
export function queryRegList () {
  return axios({
    url: 'http://' + Config.ip + ':' + Config.port + '/queryTrans/queryAgency',
    method: 'get'
  })
}
// 根据registerCode查询fundCode列表
export function queryfundList (data) {
  return axios({
    url: 'http://' + Config.ip + ':' + Config.port + '/queryTrans/queryFundCode',
    method: 'get'
  })
}

export default {
  queryRegList,
  queryfundList
}
