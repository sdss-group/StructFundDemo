import axios from 'axios'
import Config from '@/Config'
// 查询产品客户类型
function listFundCusttype () {
  return axios({
    url: 'http://' + Config.ip + ':' + Config.port + '/fundLot/listFundCusttype ',
    method: 'get'
  })
}

export default {
  listFundCusttype
}
