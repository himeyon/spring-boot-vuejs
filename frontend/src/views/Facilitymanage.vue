<template>
  <div class="animated fadeIn">
    <b-row>
      <b-col md="12" lg="12">
        <b-card>
          <div slot="header">
            <strong>2020/01/24 01:01:01</strong>
            <div class="card-header-actions">
              <b-link class="card-header-action btn-minimize" v-b-toggle.collapse1>
                <i class="icon-arrow-up"></i>
              </b-link>
            </div>
          </div>
          <b-collapse id="collapse1" visible>
          <b-row class="form-group">
            <b-col cols="6">
              <label>登録年月日from</label>
              <b-form-input type="date" id="datefrom"></b-form-input>
            </b-col>
            <b-col cols="6">
              <label>登録年月日to</label>
              <b-form-input type="date" id="dateto"></b-form-input>
            </b-col>
          </b-row>
          <b-row class="form-group">
            <b-col cols="6">
              <label>ライン名</label>
              <b-form-select id="lineNmSelect"
                :plain="true"
                :options="['','Aライン', 'Bライン', 'Cライン', 'Dライン', 'Eライン']"
                value="">
              </b-form-select>
            </b-col>
            <b-col cols="6">
              <label>工程名</label>
              <b-form-select id="kouteiNmSelect"
                :plain="true"
                :options="['','A取付', 'B取付', 'C取付', 'D取付', 'E取付']"
                value="">
              </b-form-select>
            </b-col>
          </b-row>

          <b-row class="form-group">
            <b-col cols="6">
              <label>設備No</label>
              <b-form-select id="seihinMultiSelect"
                :plain="true"
                :multiple="true"
                :options="[
                  {
                    text: '',
                    value: null
                  },
                  { text: 'A 設備1', value: 'a' }
                  ,{ text: 'A 設備2', value: 'b' }
                  ,{ text: 'A 設備3', value: 'c' }
                  ,{ text: 'A 設備4', value: 'd' }
                  ,{ text: 'A 設備5', value: 'e' }
                  ,{ text: 'A組付装置', value: 'f' }
                  ,{ text: 'A 設備1', value: 'g' }
                  ,{ text: 'A 設備2', value: 'h' }
                  ,{ text: 'A 設備3', value: 'i' }
                  ,{ text: 'A組付装置', value: 'j' }
                  ,{ text: 'A 設備1', value: 'k' }
                  ,{ text: 'A 設備2', value: 'l' }
                  ,{ text: 'A組付装置', value: 'm' }
                  ,{ text: 'A 設備1', value: 'n' }
                  ,{ text: 'A 設備2', value: 'o' }
                  ,{ text: 'A 設備3', value: 'p' }
                  ,{ text: 'A 設備4', value: 'q' }
                  ,{ text: 'A組付装置', value: 'r' }
                  ,{ text: 'A 設備1', value: 's' }
                  ,{ text: 'A 設備2', value: 't' }
                  ,{ text: 'A 設備3', value: 'u' }
                  ,{ text: 'A組付装置', value: 'v' }
                  ,{ text: 'B組付装置', value: 'w' }
                  ,{ text: 'B組付装置', value: 'x' }
                  ,{ text: 'B組付装置', value: 'y' }
                  ,{ text: 'B組付装置', value: 'z' }
                  ,{ text: 'B組付装置', value: '1' }
                  ,{ text: 'C組付装置', value: '2' }
                  ,{ text: 'C組付装置', value: '3' }
                  ,{ text: 'C組付装置', value: '4' }
                  ,{ text: 'C組付装置', value: '5' }
                  ,{ text: 'C組付装置', value: '6' }
                  ]"
                :value="[null,'c']">
              </b-form-select>
            </b-col>
            <b-col cols="6">
              <label>担当者</label>
              <b-form-select id="buzaiMultiSelect"
                :plain="true"
                :multiple="true"
                :options="[
                  { text:   '三上 一生'     , value: 'a' }
                  , { text: '吉田 拓夫'     , value: 'b' }
                  , { text: '吉沢 光保'     , value: 'c' }
                  , { text: '村瀬 智子'     , value: 'd' }
                  , { text: '武藤 克明'     , value: 'e' }
                  , { text: '浜崎 源平'     , value: 'f' }
                  ]"
                :value="[null,'c']">
              </b-form-select>
            </b-col>
          </b-row>

          <b-form-group>
            <b-button variant="primary" class="my-2 my-sm-0" type="submit">実行</b-button>
            <b-button variant="outline-success" class="my-2 my-sm-0" type="submit">リセット</b-button>
          </b-form-group>
          </b-collapse>
        </b-card>
      </b-col>
    </b-row>

    <b-row>
      <b-col lg="8" md="12">
        <c-table :table-data="targetFact" :fields="targetFactFields" caption="対象設備" :per-page=15 hover striped bordered small fixed></c-table>
      </b-col>

      <b-col lg="4" md="12">
        <b-card header="エラー分布">
          <div class="chart-wrapper">
            <error-example chartId="chart-pie-01"/>
          </div>
        </b-card>
      </b-col>
    </b-row>

    <b-row>
      <b-col lg="6">
        <b-card header="稼働率">
          <div class="chart-wrapper">
            <occupancy-example chartId="chart-pie-02"/>
          </div>
        </b-card>
      </b-col>

      <b-col lg="6">
        <c-table :table-data="facility" :fields="facilityFields" caption="" :per-page=20 hover striped bordered small fixed></c-table>
      </b-col>
    </b-row>
  </div>

</template>

<script>
import cTable from './base/Table.vue'
import ErrorExample from './charts/ErrorExample'
import OccupancyExample from './charts/OccupancyExample'

const traseInfoParentData = () => [{"linename":"Aライン","itemname":"A機械","lot":"LTA023","serialno":"14093SN0032","registdate":"2016/02/15","checkdata":"6"},{"linename":"Aライン","itemname":"A機械","lot":"LTA023","serialno":"14093SN0035","registdate":"2016/02/15","checkdata":"10"},{"linename":"Aライン","itemname":"A機械","lot":"LTA023","serialno":"14093SN0038","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lot":"LTA023","serialno":"14093SN0041","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lot":"LTA023","serialno":"14093SN0044","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lot":"LTA023","serialno":"14093SN0047","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lot":"LTA023","serialno":"14093SN0050","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lot":"LTA023","serialno":"14093SN0053","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lot":"LTA023","serialno":"14093SN0056","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lot":"LTA023","serialno":"14093SN0059","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lot":"LTA176","serialno":"35302SN0124","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lot":"LTA176","serialno":"35302SN0130","registdate":"2016/02/15","checkdata":"20"},{"linename":"Aライン","itemname":"A機械","lot":"LTA176","serialno":"35302SN0136","registdate":"2016/02/15","checkdata":"1"},{"linename":"Aライン","itemname":"A機械","lot":"LTA176","serialno":"35302SN0142","registdate":"2016/02/15","checkdata":"10"},{"linename":"Aライン","itemname":"A機械","lot":"LTA176","serialno":"35302SN0148","registdate":"2016/02/15","checkdata":"10"}]
const traceBackData = () => [{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT0352","serialnochild":"SA208620154","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT1020","serialnochild":"TA110208231","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT3502","serialnochild":"EA207300218","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT5006","serialnochild":"BA528300782","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT7652","serialnochild":"HA112600621","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT0352","serialnochild":"SA208620163","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT1020","serialnochild":"TA110208235","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT3502","serialnochild":"EA207300220","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT5006","serialnochild":"BA528300786","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT7652","serialnochild":"HA112600623","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT0352","serialnochild":"SA208620172","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT1020","serialnochild":"TA110208239","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT3502","serialnochild":"EA207300222","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT5010","serialnochild":"BA529675051","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT7652","serialnochild":"HA112600625","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT0352","serialnochild":"SA208620181","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT1020","serialnochild":"TA110208243","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT3618","serialnochild":"EA208688014","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT5010","serialnochild":"BA529675055","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT7652","serialnochild":"HA112600627","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT0352","serialnochild":"SA208620190","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT1020","serialnochild":"TA110208247","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT3618","serialnochild":"EA208688018","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT5010","serialnochild":"BA529675059","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT7652","serialnochild":"HA112600629","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT0352","serialnochild":"SA208620199","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT1020","serialnochild":"TA110208251","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT3618","serialnochild":"EA208688021","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT5010","serialnochild":"BA529675062","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT7652","serialnochild":"HA112600631","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT0411","serialnochild":"SA209664640","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT1020","serialnochild":"TA110208255","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT3811","serialnochild":"EA210165862","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT5010","serialnochild":"BA529675064","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT7652","serialnochild":"HA112600633","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT0411","serialnochild":"SA209664644","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT1020","serialnochild":"TA110208259","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT3811","serialnochild":"EA210165865","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT5021","serialnochild":"BA533011002","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT7652","serialnochild":"HA112600635","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT0411","serialnochild":"SA209664648","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT1020","serialnochild":"TA110208263","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT3811","serialnochild":"EA210165868","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT5021","serialnochild":"BA533011006","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT7652","serialnochild":"HA112600637","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT0781","serialnochild":"SA217676002","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT1020","serialnochild":"TA110208267","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT3811","serialnochild":"EA210165871","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT5021","serialnochild":"BA533011008","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT7652","serialnochild":"HA112600639","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT0781","serialnochild":"SA217676006","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT1832","serialnochild":"TA532990872","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT3811","serialnochild":"EA210165874","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT5021","serialnochild":"BA533011012","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT7652","serialnochild":"HA112600641","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT0781","serialnochild":"SA217676010","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT1832","serialnochild":"TA532990886","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT3811","serialnochild":"EA210165877","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT5165","serialnochild":"BA656085030","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT7652","serialnochild":"HA112600643","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0136","lotchild":"LT0781","serialnochild":"SA217676014","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT1832","serialnochild":"TA532990900","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT3811","serialnochild":"EA210165880","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT5165","serialnochild":"BA656085034","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT7652","serialnochild":"HA112600645","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT0781","serialnochild":"SA217676018","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT1832","serialnochild":"TA532990914","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT4560","serialnochild":"EA210654066","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT5165","serialnochild":"BA656085038","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT7652","serialnochild":"HA112600647","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT0781","serialnochild":"SA217676022","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT1832","serialnochild":"TA532990928","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT4560","serialnochild":"EA210654078","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT5165","serialnochild":"BA656085042","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT7652","serialnochild":"HA112600649","checkdata":"2"}]
const traceInfoChildData = () => [{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT0352","serialnochild":"SA208620154","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT1020","serialnochild":"TA110208231","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT3502","serialnochild":"EA207300218","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT5006","serialnochild":"BA528300782","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT7652","serialnochild":"HA112600621","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT0352","serialnochild":"SA208620163","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT1020","serialnochild":"TA110208235","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT3502","serialnochild":"EA207300220","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT5006","serialnochild":"BA528300786","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT7652","serialnochild":"HA112600623","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT0352","serialnochild":"SA208620172","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT1020","serialnochild":"TA110208239","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT3502","serialnochild":"EA207300222","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT5010","serialnochild":"BA529675051","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT7652","serialnochild":"HA112600625","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT0352","serialnochild":"SA208620181","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT1020","serialnochild":"TA110208243","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT3618","serialnochild":"EA208688014","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT5010","serialnochild":"BA529675055","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT7652","serialnochild":"HA112600627","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT0352","serialnochild":"SA208620190","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT1020","serialnochild":"TA110208247","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT3618","serialnochild":"EA208688018","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT5010","serialnochild":"BA529675059","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT7652","serialnochild":"HA112600629","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT0352","serialnochild":"SA208620199","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT1020","serialnochild":"TA110208251","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT3618","serialnochild":"EA208688021","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT5010","serialnochild":"BA529675062","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT7652","serialnochild":"HA112600631","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT0411","serialnochild":"SA209664640","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT1020","serialnochild":"TA110208255","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT3811","serialnochild":"EA210165862","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT5010","serialnochild":"BA529675064","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT7652","serialnochild":"HA112600633","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT0411","serialnochild":"SA209664644","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT1020","serialnochild":"TA110208259","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT3811","serialnochild":"EA210165865","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT5021","serialnochild":"BA533011002","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT7652","serialnochild":"HA112600635","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT0411","serialnochild":"SA209664648","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT1020","serialnochild":"TA110208263","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT3811","serialnochild":"EA210165868","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT5021","serialnochild":"BA533011006","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT7652","serialnochild":"HA112600637","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT0781","serialnochild":"SA217676002","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT1020","serialnochild":"TA110208267","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT3811","serialnochild":"EA210165871","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT5021","serialnochild":"BA533011008","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT7652","serialnochild":"HA112600639","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT0781","serialnochild":"SA217676006","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT1832","serialnochild":"TA532990872","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT3811","serialnochild":"EA210165874","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT5021","serialnochild":"BA533011012","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT7652","serialnochild":"HA112600641","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT0781","serialnochild":"SA217676010","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT1832","serialnochild":"TA532990886","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT3811","serialnochild":"EA210165877","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT5165","serialnochild":"BA656085030","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT7652","serialnochild":"HA112600643","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0136","lotchild":"LT0781","serialnochild":"SA217676014","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT1832","serialnochild":"TA532990900","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT3811","serialnochild":"EA210165880","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT5165","serialnochild":"BA656085034","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT7652","serialnochild":"HA112600645","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT0781","serialnochild":"SA217676018","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT1832","serialnochild":"TA532990914","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT4560","serialnochild":"EA210654066","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT5165","serialnochild":"BA656085038","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT7652","serialnochild":"HA112600647","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT0781","serialnochild":"SA217676022","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT1832","serialnochild":"TA532990928","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT4560","serialnochild":"EA210654078","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT5165","serialnochild":"BA656085042","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT7652","serialnochild":"HA112600649","checkdata":"2"}]
const traceForwardData = () => [{"linename":"Aライン","itemname":"A機械","lotparent":"LTA023","serialnoparent":"14093SN0032","registdate":"2016/02/15","checkdata":"6"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA023","serialnoparent":"14093SN0035","registdate":"2016/02/15","checkdata":"10"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA023","serialnoparent":"14093SN0038","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA023","serialnoparent":"14093SN0041","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA023","serialnoparent":"14093SN0044","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA023","serialnoparent":"14093SN0047","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA023","serialnoparent":"14093SN0050","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA023","serialnoparent":"14093SN0053","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA023","serialnoparent":"14093SN0056","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA023","serialnoparent":"14093SN0059","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA176","serialnoparent":"35302SN0124","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA176","serialnoparent":"35302SN0130","registdate":"2016/02/15","checkdata":"20"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA176","serialnoparent":"35302SN0136","registdate":"2016/02/15","checkdata":"1"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA176","serialnoparent":"35302SN0142","registdate":"2016/02/15","checkdata":"10"},{"linename":"Aライン","itemname":"A機械","lotparent":"LTA176","serialnoparent":"35302SN0148","registdate":"2016/02/15","checkdata":"10"}]

const targetFactData = () => [{"facno":"MT1001","facname":"A組付装置","username":"古田 拓夫","nextcheckdate":"2016/02/10","checkurl":"http://test.co.jp","checkdata":"MT1001"},{"facno":"MT1002","facname":"A組付装置","username":"武藤 克明","nextcheckdate":"2016/02/18","checkurl":"http://test.co.jp","checkdata":"MT1002"},{"facno":"MT1003","facname":"A組付装置","username":"浜崎 源平","nextcheckdate":"2016/05/21","checkurl":"http://test.co.jp","checkdata":"MT1003"},{"facno":"MT1004","facname":"A組付装置","username":"吉沢 光保","nextcheckdate":"2016/06/17","checkurl":"http://test.co.jp","checkdata":"MT1004"},{"facno":"MT1005","facname":"A組付装置","username":"村瀬 智子","nextcheckdate":"2016/10/22","checkurl":"http://test.co.jp","checkdata":"MT1005"},{"facno":"MT2011","facname":"B組付装置","username":"古田 拓夫","nextcheckdate":"2016/07/14","checkurl":"http://test.co.jp","checkdata":"MT2011"},{"facno":"MT2012","facname":"B組付装置","username":"武藤 克明","nextcheckdate":"2016/06/17","checkurl":"http://test.co.jp","checkdata":"MT2012"},{"facno":"MT2013","facname":"B組付装置","username":"浜崎 源平","nextcheckdate":"2016/06/30","checkurl":"http://test.co.jp","checkdata":"MT2013"},{"facno":"MT2014","facname":"B組付装置","username":"吉沢 光保","nextcheckdate":"2016/08/27","checkurl":"http://test.co.jp","checkdata":"MT2014"},{"facno":"MT2015","facname":"B組付装置","username":"村瀬 智子","nextcheckdate":"2016/09/24","checkurl":"http://test.co.jp","checkdata":"MT2015"},{"facno":"MT3021","facname":"C組付装置","username":"古田 拓夫","nextcheckdate":"2016/07/16","checkurl":"http://test.co.jp","checkdata":"MT3021"},{"facno":"MT3022","facname":"C組付装置","username":"武藤 克明","nextcheckdate":"2016/05/28","checkurl":"http://test.co.jp","checkdata":"MT3022"},{"facno":"MT3023","facname":"C組付装置","username":"浜崎 源平","nextcheckdate":"2016/08/27","checkurl":"http://test.co.jp","checkdata":"MT3023"},{"facno":"MT3024","facname":"C組付装置","username":"吉沢 光保","nextcheckdate":"2016/11/26","checkurl":"http://test.co.jp","checkdata":"MT3024"},{"facno":"MT3025","facname":"C組付装置","username":"村瀬 智子","nextcheckdate":"2016/11/05","checkurl":"http://test.co.jp","checkdata":"MT3025"},{"facno":"MT4001","facname":"A 設備1","username":"古田 拓夫","nextcheckdate":"2016/12/24","checkurl":"http://test.co.jp","checkdata":"MT4001"},{"facno":"MT4002","facname":"A 設備2","username":"古田 拓夫","nextcheckdate":"2016/08/25","checkurl":"http://test.co.jp","checkdata":"MT4002"},{"facno":"MT4003","facname":"A 設備3","username":"古田 拓夫","nextcheckdate":"2016/10/27","checkurl":"http://test.co.jp","checkdata":"MT4003"},{"facno":"MT5011","facname":"A 設備1","username":"吉沢 光保","nextcheckdate":"2016/04/03","checkurl":"http://test.co.jp","checkdata":"MT5011"},{"facno":"MT5012","facname":"A 設備2","username":"吉沢 光保","nextcheckdate":"2016/04/18","checkurl":"http://test.co.jp","checkdata":"MT5012"},{"facno":"MT5013","facname":"A 設備3","username":"吉沢 光保","nextcheckdate":"2016/05/21","checkurl":"http://test.co.jp","checkdata":"MT5013"},{"facno":"MT6001","facname":"A 設備1","username":"武藤 克明","nextcheckdate":"2016/06/17","checkurl":"http://test.co.jp","checkdata":"MT6001"},{"facno":"MT6002","facname":"A 設備2","username":"武藤 克明","nextcheckdate":"2016/10/22","checkurl":"http://test.co.jp","checkdata":"MT6002"},{"facno":"MT6003","facname":"A 設備3","username":"武藤 克明","nextcheckdate":"2016/07/14","checkurl":"http://test.co.jp","checkdata":"MT6003"},{"facno":"MT6004","facname":"A 設備4","username":"武藤 克明","nextcheckdate":"2016/06/17","checkurl":"http://test.co.jp","checkdata":"MT6004"},{"facno":"MT6005","facname":"A 設備5","username":"武藤 克明","nextcheckdate":"2016/06/30","checkurl":"http://test.co.jp","checkdata":"MT6005"},{"facno":"MT7021","facname":"A 設備1","username":"村瀬 智子","nextcheckdate":"2016/08/27","checkurl":"http://test.co.jp","checkdata":"MT7021"},{"facno":"MT7022","facname":"A 設備2","username":"村瀬 智子","nextcheckdate":"2016/09/24","checkurl":"http://test.co.jp","checkdata":"MT7022"},{"facno":"MT7023","facname":"A 設備3","username":"村瀬 智子","nextcheckdate":"2016/07/16","checkurl":"http://test.co.jp","checkdata":"MT7023"},{"facno":"MT7024","facname":"A 設備4","username":"村瀬 智子","nextcheckdate":"2016/05/28","checkurl":"http://test.co.jp","checkdata":"MT7024"},{"facno":"MT8001","facname":"A 設備1","username":"三上 一生","nextcheckdate":"2016/08/27","checkurl":"http://test.co.jp","checkdata":"MT8001"},{"facno":"MT8002","facname":"A 設備2","username":"三上 一生","nextcheckdate":"2016/11/26","checkurl":"http://test.co.jp","checkdata":"MT8002"}]
const facilityData = () => [{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"100","power":"1100"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"600"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT1004","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"200"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT1005","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"40","power":"39"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT2011","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"121"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT2012","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"100","power":"33"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT2013","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"48"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT2014","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"0"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT2015","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"40","power":"88"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT3021","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"91"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT3022","errorcd":"ER012","pulse":"1","vibration":"1","shotcount":"20","power":"133"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT3023","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"97"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT3024","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"0"},
{"getdate":"2016/02/15 ","gettime":"09:01","facno":"MT3025","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"40","power":"55"},
{"getdate":"2016/02/15 ","gettime":"09:02","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:02","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1128"},
{"getdate":"2016/02/15 ","gettime":"09:02","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"625"},
{"getdate":"2016/02/15 ","gettime":"09:03","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:03","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"99","power":"1087"},
{"getdate":"2016/02/15 ","gettime":"09:03","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"600"},
{"getdate":"2016/02/15 ","gettime":"09:04","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"09:04","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"103","power":"1115"},
{"getdate":"2016/02/15 ","gettime":"09:04","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"632"},
{"getdate":"2016/02/15 ","gettime":"09:05","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"09:05","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"98","power":"1124"},
{"getdate":"2016/02/15 ","gettime":"09:05","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"630"},
{"getdate":"2016/02/15 ","gettime":"09:06","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"09:06","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1116"},
{"getdate":"2016/02/15 ","gettime":"09:06","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"624"},
{"getdate":"2016/02/15 ","gettime":"09:07","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"09:07","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"99","power":"1094"},
{"getdate":"2016/02/15 ","gettime":"09:07","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"625"},
{"getdate":"2016/02/15 ","gettime":"09:08","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"58"},
{"getdate":"2016/02/15 ","gettime":"09:08","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"97","power":"1116"},
{"getdate":"2016/02/15 ","gettime":"09:08","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"606"},
{"getdate":"2016/02/15 ","gettime":"09:09","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"59"},
{"getdate":"2016/02/15 ","gettime":"09:09","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"103","power":"1095"},
{"getdate":"2016/02/15 ","gettime":"09:09","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"628"},
{"getdate":"2016/02/15 ","gettime":"09:10","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"58"},
{"getdate":"2016/02/15 ","gettime":"09:10","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"100","power":"1120"},
{"getdate":"2016/02/15 ","gettime":"09:10","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"612"},
{"getdate":"2016/02/15 ","gettime":"09:11","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:11","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"103","power":"1095"},
{"getdate":"2016/02/15 ","gettime":"09:11","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"608"},
{"getdate":"2016/02/15 ","gettime":"09:12","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:12","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"97","power":"1122"},
{"getdate":"2016/02/15 ","gettime":"09:12","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"603"},
{"getdate":"2016/02/15 ","gettime":"09:13","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:13","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1120"},
{"getdate":"2016/02/15 ","gettime":"09:13","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"592"},
{"getdate":"2016/02/15 ","gettime":"09:14","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"09:14","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"103","power":"1094"},
{"getdate":"2016/02/15 ","gettime":"09:14","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"627"},
{"getdate":"2016/02/15 ","gettime":"09:15","facno":"MT1001","errorcd":"ER010","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:15","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"103","power":"1116"},
{"getdate":"2016/02/15 ","gettime":"09:15","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"632"},
{"getdate":"2016/02/15 ","gettime":"09:16","facno":"MT1001","errorcd":"ER010","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"09:16","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1108"},
{"getdate":"2016/02/15 ","gettime":"09:16","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"599"},
{"getdate":"2016/02/15 ","gettime":"09:17","facno":"MT1001","errorcd":"ER010","pulse":"1","vibration":"1","shotcount":"0","power":"62"},
{"getdate":"2016/02/15 ","gettime":"09:17","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"97","power":"1090"},
{"getdate":"2016/02/15 ","gettime":"09:17","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"613"},
{"getdate":"2016/02/15 ","gettime":"09:18","facno":"MT1001","errorcd":"ER010","pulse":"1","vibration":"1","shotcount":"0","power":"62"},
{"getdate":"2016/02/15 ","gettime":"09:18","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"98","power":"1126"},
{"getdate":"2016/02/15 ","gettime":"09:18","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"632"},
{"getdate":"2016/02/15 ","gettime":"09:19","facno":"MT1001","errorcd":"ER010","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"09:19","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"98","power":"1107"},
{"getdate":"2016/02/15 ","gettime":"09:19","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"582"},
{"getdate":"2016/02/15 ","gettime":"09:20","facno":"MT1001","errorcd":"ER010","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"09:20","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"101","power":"1120"},
{"getdate":"2016/02/15 ","gettime":"09:20","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"624"},
{"getdate":"2016/02/15 ","gettime":"09:21","facno":"MT1001","errorcd":"ER010","pulse":"1","vibration":"1","shotcount":"0","power":"59"},
{"getdate":"2016/02/15 ","gettime":"09:21","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"101","power":"1121"},
{"getdate":"2016/02/15 ","gettime":"09:21","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"617"},
{"getdate":"2016/02/15 ","gettime":"09:22","facno":"MT1001","errorcd":"ER010","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"09:22","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"98","power":"1096"},
{"getdate":"2016/02/15 ","gettime":"09:22","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"614"},
{"getdate":"2016/02/15 ","gettime":"09:23","facno":"MT1001","errorcd":"ER010","pulse":"1","vibration":"1","shotcount":"0","power":"62"},
{"getdate":"2016/02/15 ","gettime":"09:23","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"103","power":"1081"},
{"getdate":"2016/02/15 ","gettime":"09:23","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"583"},
{"getdate":"2016/02/15 ","gettime":"09:24","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"58"},
{"getdate":"2016/02/15 ","gettime":"09:24","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1128"},
{"getdate":"2016/02/15 ","gettime":"09:24","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"627"},
{"getdate":"2016/02/15 ","gettime":"09:25","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"09:25","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1105"},
{"getdate":"2016/02/15 ","gettime":"09:25","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"615"},
{"getdate":"2016/02/15 ","gettime":"09:26","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:26","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"99","power":"1097"},
{"getdate":"2016/02/15 ","gettime":"09:26","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"595"},
{"getdate":"2016/02/15 ","gettime":"09:27","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"09:27","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"97","power":"1090"},
{"getdate":"2016/02/15 ","gettime":"09:27","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"611"},
{"getdate":"2016/02/15 ","gettime":"09:28","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"58"},
{"getdate":"2016/02/15 ","gettime":"09:28","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"103","power":"1106"},
{"getdate":"2016/02/15 ","gettime":"09:28","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"613"},
{"getdate":"2016/02/15 ","gettime":"09:29","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"62"},
{"getdate":"2016/02/15 ","gettime":"09:29","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1084"},
{"getdate":"2016/02/15 ","gettime":"09:29","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"587"},
{"getdate":"2016/02/15 ","gettime":"09:30","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"09:30","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"100","power":"1084"},
{"getdate":"2016/02/15 ","gettime":"09:30","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"608"},
{"getdate":"2016/02/15 ","gettime":"09:31","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"62"},
{"getdate":"2016/02/15 ","gettime":"09:31","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"99","power":"1108"},
{"getdate":"2016/02/15 ","gettime":"09:31","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"599"},
{"getdate":"2016/02/15 ","gettime":"09:32","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"09:32","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1127"},
{"getdate":"2016/02/15 ","gettime":"09:32","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"616"},
{"getdate":"2016/02/15 ","gettime":"09:33","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"59"},
{"getdate":"2016/02/15 ","gettime":"09:33","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1122"},
{"getdate":"2016/02/15 ","gettime":"09:33","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"622"},
{"getdate":"2016/02/15 ","gettime":"09:34","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"09:34","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1121"},
{"getdate":"2016/02/15 ","gettime":"09:34","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"581"},
{"getdate":"2016/02/15 ","gettime":"09:35","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"09:35","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"98","power":"1099"},
{"getdate":"2016/02/15 ","gettime":"09:35","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"632"},
{"getdate":"2016/02/15 ","gettime":"09:36","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"58"},
{"getdate":"2016/02/15 ","gettime":"09:36","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"99","power":"1095"},
{"getdate":"2016/02/15 ","gettime":"09:36","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"624"},
{"getdate":"2016/02/15 ","gettime":"09:37","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:37","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"99","power":"1101"},
{"getdate":"2016/02/15 ","gettime":"09:37","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"628"},
{"getdate":"2016/02/15 ","gettime":"09:38","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"09:38","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"99","power":"1082"},
{"getdate":"2016/02/15 ","gettime":"09:38","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"627"},
{"getdate":"2016/02/15 ","gettime":"09:39","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"09:39","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"97","power":"1122"},
{"getdate":"2016/02/15 ","gettime":"09:39","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"585"},
{"getdate":"2016/02/15 ","gettime":"09:40","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"09:40","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"97","power":"1104"},
{"getdate":"2016/02/15 ","gettime":"09:40","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"611"},
{"getdate":"2016/02/15 ","gettime":"09:41","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:41","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"97","power":"1097"},
{"getdate":"2016/02/15 ","gettime":"09:41","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"595"},
{"getdate":"2016/02/15 ","gettime":"09:42","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:42","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"100","power":"1118"},
{"getdate":"2016/02/15 ","gettime":"09:42","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"583"},
{"getdate":"2016/02/15 ","gettime":"09:43","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"09:43","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"101","power":"1086"},
{"getdate":"2016/02/15 ","gettime":"09:43","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"589"},
{"getdate":"2016/02/15 ","gettime":"09:44","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:44","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"97","power":"1127"},
{"getdate":"2016/02/15 ","gettime":"09:44","facno":"MT1003","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"607"},
{"getdate":"2016/02/15 ","gettime":"09:45","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"62"},
{"getdate":"2016/02/15 ","gettime":"09:45","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"97","power":"1106"},
{"getdate":"2016/02/15 ","gettime":"09:46","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"58"},
{"getdate":"2016/02/15 ","gettime":"09:46","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"101","power":"1100"},
{"getdate":"2016/02/15 ","gettime":"09:47","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"59"},
{"getdate":"2016/02/15 ","gettime":"09:47","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"100","power":"1084"},
{"getdate":"2016/02/15 ","gettime":"09:48","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"09:48","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"101","power":"1114"},
{"getdate":"2016/02/15 ","gettime":"09:49","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"09:49","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"97","power":"1098"},
{"getdate":"2016/02/15 ","gettime":"09:50","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"62"},
{"getdate":"2016/02/15 ","gettime":"09:50","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"98","power":"1097"},
{"getdate":"2016/02/15 ","gettime":"09:51","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"09:51","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"99","power":"1108"},
{"getdate":"2016/02/15 ","gettime":"09:52","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:52","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1107"},
{"getdate":"2016/02/15 ","gettime":"09:53","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"09:53","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"99","power":"1089"},
{"getdate":"2016/02/15 ","gettime":"09:54","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"59"},
{"getdate":"2016/02/15 ","gettime":"09:54","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"100","power":"1106"},
{"getdate":"2016/02/15 ","gettime":"09:55","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"09:55","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"103","power":"1081"},
{"getdate":"2016/02/15 ","gettime":"09:56","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"09:56","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"101","power":"1099"},
{"getdate":"2016/02/15 ","gettime":"09:57","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"09:57","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"99","power":"1110"},
{"getdate":"2016/02/15 ","gettime":"09:58","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"09:58","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"102","power":"1094"},
{"getdate":"2016/02/15 ","gettime":"09:59","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"62"},
{"getdate":"2016/02/15 ","gettime":"09:59","facno":"MT1002","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"103","power":"1096"},
{"getdate":"2016/02/15 ","gettime":"10:00","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"58"},
{"getdate":"2016/02/15 ","gettime":"10:01","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"10:02","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"10:03","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"62"},
{"getdate":"2016/02/15 ","gettime":"10:04","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"60"},
{"getdate":"2016/02/15 ","gettime":"10:05","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"59"},
{"getdate":"2016/02/15 ","gettime":"10:06","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"10:07","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"10:08","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"10:09","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"10:10","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"58"},
{"getdate":"2016/02/15 ","gettime":"10:11","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"63"},
{"getdate":"2016/02/15 ","gettime":"10:12","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"57"},
{"getdate":"2016/02/15 ","gettime":"10:13","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"62"},
{"getdate":"2016/02/15 ","gettime":"10:14","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"61"},
{"getdate":"2016/02/15 ","gettime":"10:15","facno":"MT1001","errorcd":"正常","pulse":"1","vibration":"1","shotcount":"0","power":"60"}]




export default {
  name: 'Facilitymanage',
  components: {cTable, ErrorExample, OccupancyExample},
  data: () => {
    return {
      targetFact: targetFactData(),
      targetFactFields: [
        {key: 'facno',         label: '設備No'},
        {key: 'facname',       label: '設備名称'},
        {key: 'username',      label: '担当者',   sortable: true},
        {key: 'nextcheckdate', label: '次回点検日'},
        {key: 'checkurl',      label: '点検マニュアル',       sortable: true},
        {key: 'checkdata',     label: '故障履歴',   sortable: true}
      ],
      facility: facilityData(),
      facilityFields: [
        {key: 'getdate',       label: '取得日'},
        {key: 'gettime',       label: '取得時刻'},
        {key: 'facno',         label: '設備No',   sortable: true},
        {key: 'errorcd',       label: 'エラーコード'},
        {key: 'pulse',         label: 'パルス',       sortable: true},
        {key: 'vibration',     label: '振動',   sortable: true},
        {key: 'shotcount',     label: 'ショット数',   sortable: true},
        {key: 'power',         label: '使用電力',   sortable: true}
      ]
    }
  }
}
</script>
