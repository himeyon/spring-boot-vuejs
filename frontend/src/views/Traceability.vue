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
              <label>製品品番</label>
              <b-form-select id="seihinMultiSelect"
                :plain="true"
                :multiple="true"
                :options="[
                  {
                    text: '',
                    value: null
                  },
                  {
                    text: 'PM0001',
                    value: 'a'
                  }, {
                    text: 'PM0301',
                    value: 'b'
                  }, {
                    text: 'PM0501',
                    value: 'c'
                  }]"
                :value="[null,'c']">
              </b-form-select>
            </b-col>
            <b-col cols="6">
              <label>部材品番</label>
              <b-form-select id="buzaiMultiSelect"
                :plain="true"
                :multiple="true"
                :options="[
                  {
                    text: '',
                    value: null
                  },
                  {
                    text: 'PM0001',
                    value: 'a'
                  }, {
                    text: 'PM0301',
                    value: 'b'
                  }]"
                :value="[null,'c']">
              </b-form-select>
            </b-col>
          </b-row>

          <b-row class="form-group">
            <b-col cols="6">
              <label>親シリアルNo</label>
              <b-form-select id="oyaSeriMultiSelect"
                :plain="true"
                :multiple="true"
                :options="[
                  {
                    text: '',
                    value: null
                  },
                  {
                    text: '14093SN0032',
                    value: 'a'
                  }, {
                    text: '14093SN0035',
                    value: 'b'
                  }, {
                    text: '14093SN0038',
                    value: 'c'
                  }]"
                :value="[null,'c']">
              </b-form-select>
            </b-col>
            <b-col cols="6">
              <label>子シリアルNo</label>
              <b-form-select id="koMultiSelect"
                :plain="true"
                :multiple="true"
                :options="[
                  {
                    text: '',
                    value: null
                  },
                  {
                    text: 'SA208620154',
                    value: 'a'
                  }, {
                    text: 'TA110208231',
                    value: 'b'
                  }]"
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
      <b-col lg="6">
        <c-table :table-data="traceInfoParent" :fields="traceInfoParentFields" caption="トレース情報（親）" :per-page=10 hover bordered small fixed></c-table>
      </b-col>

      <b-col lg="6">
        <c-table :table-data="traceBack" :fields="traceBackFields" caption="トレースバック結果" :per-page=10 hover striped bordered small fixed></c-table>
      </b-col>
    </b-row>

    <b-row>
      <b-col lg="6">
        <c-table :table-data="traceInfoChild" :fields="traceInfoChildFields" caption="トレース情報（子）" :per-page=10 hover bordered small fixed></c-table>
      </b-col>

      <b-col lg="6">
        <c-table :table-data="traceForward" :fields="traceForwardFields" caption="トレースフォワード結果" :per-page=10 hover striped bordered small fixed></c-table>
      </b-col>
    </b-row>
  </div>

</template>

<script>
import cTable from './base/Table.vue'

const traseInfoParentData = () => [{"linename":"Aライン","itemname":"A自動車","lot":"LTA023","serialno":"14093SN0032","registdate":"2016/02/15","checkdata":"6"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA023","serialno":"14093SN0035","registdate":"2016/02/15","checkdata":"10"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA023","serialno":"14093SN0038","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA023","serialno":"14093SN0041","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA023","serialno":"14093SN0044","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA023","serialno":"14093SN0047","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA023","serialno":"14093SN0050","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA023","serialno":"14093SN0053","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA023","serialno":"14093SN0056","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA023","serialno":"14093SN0059","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA176","serialno":"35302SN0124","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA176","serialno":"35302SN0130","registdate":"2016/02/15","checkdata":"20"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA176","serialno":"35302SN0136","registdate":"2016/02/15","checkdata":"1"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA176","serialno":"35302SN0142","registdate":"2016/02/15","checkdata":"10"},{"linename":"Aライン","itemname":"A自動車","lot":"LTA176","serialno":"35302SN0148","registdate":"2016/02/15","checkdata":"10"}]
const traceBackData = () => [{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT0352","serialnochild":"SA208620154","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT1020","serialnochild":"TA110208231","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT3502","serialnochild":"EA207300218","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT5006","serialnochild":"BA528300782","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT7652","serialnochild":"HA112600621","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT0352","serialnochild":"SA208620163","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT1020","serialnochild":"TA110208235","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT3502","serialnochild":"EA207300220","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT5006","serialnochild":"BA528300786","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT7652","serialnochild":"HA112600623","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT0352","serialnochild":"SA208620172","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT1020","serialnochild":"TA110208239","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT3502","serialnochild":"EA207300222","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT5010","serialnochild":"BA529675051","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT7652","serialnochild":"HA112600625","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT0352","serialnochild":"SA208620181","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT1020","serialnochild":"TA110208243","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT3618","serialnochild":"EA208688014","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT5010","serialnochild":"BA529675055","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT7652","serialnochild":"HA112600627","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT0352","serialnochild":"SA208620190","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT1020","serialnochild":"TA110208247","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT3618","serialnochild":"EA208688018","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT5010","serialnochild":"BA529675059","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT7652","serialnochild":"HA112600629","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT0352","serialnochild":"SA208620199","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT1020","serialnochild":"TA110208251","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT3618","serialnochild":"EA208688021","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT5010","serialnochild":"BA529675062","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT7652","serialnochild":"HA112600631","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT0411","serialnochild":"SA209664640","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT1020","serialnochild":"TA110208255","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT3811","serialnochild":"EA210165862","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT5010","serialnochild":"BA529675064","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT7652","serialnochild":"HA112600633","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT0411","serialnochild":"SA209664644","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT1020","serialnochild":"TA110208259","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT3811","serialnochild":"EA210165865","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT5021","serialnochild":"BA533011002","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT7652","serialnochild":"HA112600635","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT0411","serialnochild":"SA209664648","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT1020","serialnochild":"TA110208263","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT3811","serialnochild":"EA210165868","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT5021","serialnochild":"BA533011006","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT7652","serialnochild":"HA112600637","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT0781","serialnochild":"SA217676002","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT1020","serialnochild":"TA110208267","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT3811","serialnochild":"EA210165871","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT5021","serialnochild":"BA533011008","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT7652","serialnochild":"HA112600639","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT0781","serialnochild":"SA217676006","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT1832","serialnochild":"TA532990872","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT3811","serialnochild":"EA210165874","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT5021","serialnochild":"BA533011012","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT7652","serialnochild":"HA112600641","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT0781","serialnochild":"SA217676010","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT1832","serialnochild":"TA532990886","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT3811","serialnochild":"EA210165877","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT5165","serialnochild":"BA656085030","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT7652","serialnochild":"HA112600643","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0136","lotchild":"LT0781","serialnochild":"SA217676014","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT1832","serialnochild":"TA532990900","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT3811","serialnochild":"EA210165880","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT5165","serialnochild":"BA656085034","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT7652","serialnochild":"HA112600645","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT0781","serialnochild":"SA217676018","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT1832","serialnochild":"TA532990914","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT4560","serialnochild":"EA210654066","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT5165","serialnochild":"BA656085038","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT7652","serialnochild":"HA112600647","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT0781","serialnochild":"SA217676022","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT1832","serialnochild":"TA532990928","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT4560","serialnochild":"EA210654078","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT5165","serialnochild":"BA656085042","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT7652","serialnochild":"HA112600649","checkdata":"2"}]
const traceInfoChildData = () => [{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT0352","serialnochild":"SA208620154","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT1020","serialnochild":"TA110208231","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT3502","serialnochild":"EA207300218","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT5006","serialnochild":"BA528300782","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0032","lotchild":"LT7652","serialnochild":"HA112600621","checkdata":"1"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT0352","serialnochild":"SA208620163","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT1020","serialnochild":"TA110208235","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT3502","serialnochild":"EA207300220","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT5006","serialnochild":"BA528300786","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0035","lotchild":"LT7652","serialnochild":"HA112600623","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT0352","serialnochild":"SA208620172","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT1020","serialnochild":"TA110208239","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT3502","serialnochild":"EA207300222","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT5010","serialnochild":"BA529675051","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0038","lotchild":"LT7652","serialnochild":"HA112600625","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT0352","serialnochild":"SA208620181","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT1020","serialnochild":"TA110208243","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT3618","serialnochild":"EA208688014","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT5010","serialnochild":"BA529675055","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0041","lotchild":"LT7652","serialnochild":"HA112600627","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT0352","serialnochild":"SA208620190","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT1020","serialnochild":"TA110208247","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT3618","serialnochild":"EA208688018","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT5010","serialnochild":"BA529675059","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0044","lotchild":"LT7652","serialnochild":"HA112600629","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT0352","serialnochild":"SA208620199","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT1020","serialnochild":"TA110208251","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT3618","serialnochild":"EA208688021","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT5010","serialnochild":"BA529675062","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0047","lotchild":"LT7652","serialnochild":"HA112600631","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT0411","serialnochild":"SA209664640","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT1020","serialnochild":"TA110208255","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT3811","serialnochild":"EA210165862","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT5010","serialnochild":"BA529675064","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0050","lotchild":"LT7652","serialnochild":"HA112600633","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT0411","serialnochild":"SA209664644","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT1020","serialnochild":"TA110208259","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT3811","serialnochild":"EA210165865","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT5021","serialnochild":"BA533011002","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0053","lotchild":"LT7652","serialnochild":"HA112600635","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT0411","serialnochild":"SA209664648","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT1020","serialnochild":"TA110208263","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT3811","serialnochild":"EA210165868","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT5021","serialnochild":"BA533011006","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0056","lotchild":"LT7652","serialnochild":"HA112600637","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT0781","serialnochild":"SA217676002","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT1020","serialnochild":"TA110208267","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT3811","serialnochild":"EA210165871","checkdata":"2"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT5021","serialnochild":"BA533011008","checkdata":"3"},{"lotparent":"LTA023","serialnoparent":"14093SN0059","lotchild":"LT7652","serialnochild":"HA112600639","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT0781","serialnochild":"SA217676006","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT1832","serialnochild":"TA532990872","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT3811","serialnochild":"EA210165874","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT5021","serialnochild":"BA533011012","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0124","lotchild":"LT7652","serialnochild":"HA112600641","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT0781","serialnochild":"SA217676010","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT1832","serialnochild":"TA532990886","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT3811","serialnochild":"EA210165877","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT5165","serialnochild":"BA656085030","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT7652","serialnochild":"HA112600643","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0136","lotchild":"LT0781","serialnochild":"SA217676014","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT1832","serialnochild":"TA532990900","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT3811","serialnochild":"EA210165880","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT5165","serialnochild":"BA656085034","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0130","lotchild":"LT7652","serialnochild":"HA112600645","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT0781","serialnochild":"SA217676018","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT1832","serialnochild":"TA532990914","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT4560","serialnochild":"EA210654066","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT5165","serialnochild":"BA656085038","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0142","lotchild":"LT7652","serialnochild":"HA112600647","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT0781","serialnochild":"SA217676022","checkdata":"1"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT1832","serialnochild":"TA532990928","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT4560","serialnochild":"EA210654078","checkdata":"2"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT5165","serialnochild":"BA656085042","checkdata":"3"},{"lotparent":"LTA176","serialnoparent":"35302SN0148","lotchild":"LT7652","serialnochild":"HA112600649","checkdata":"2"}]
const traceForwardData = () => [{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA023","serialnoparent":"14093SN0032","registdate":"2016/02/15","checkdata":"6"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA023","serialnoparent":"14093SN0035","registdate":"2016/02/15","checkdata":"10"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA023","serialnoparent":"14093SN0038","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA023","serialnoparent":"14093SN0041","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA023","serialnoparent":"14093SN0044","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA023","serialnoparent":"14093SN0047","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA023","serialnoparent":"14093SN0050","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA023","serialnoparent":"14093SN0053","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA023","serialnoparent":"14093SN0056","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA023","serialnoparent":"14093SN0059","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA176","serialnoparent":"35302SN0124","registdate":"2016/02/15","checkdata":"11"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA176","serialnoparent":"35302SN0130","registdate":"2016/02/15","checkdata":"20"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA176","serialnoparent":"35302SN0136","registdate":"2016/02/15","checkdata":"1"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA176","serialnoparent":"35302SN0142","registdate":"2016/02/15","checkdata":"10"},{"linename":"Aライン","itemname":"A自動車","lotparent":"LTA176","serialnoparent":"35302SN0148","registdate":"2016/02/15","checkdata":"10"}]

export default {
  name: 'Traceability',
  components: {cTable},
  data: () => {
    return {
      traceInfoParent: traseInfoParentData(),
      traceInfoParentFields: [
        {key: 'linename',   label: 'ライン名'},
        {key: 'itemname',   label: '製品品番'},
        {key: 'lot',        label: '親ロットNo',   sortable: true},
        {key: 'serialno',   label: '親シリアルNo'},
        {key: 'registdate', label: '登録日',       sortable: true},
        {key: 'checkdata',  label: '検査データ',   sortable: true}
      ],
      traceBack: traceBackData(),
      traceBackFields: [
        {key: 'lotparent',        label: '親ロットNo'},
        {key: 'serialnoparent',   label: '親シリアルNo'},
        {key: 'lotchild',         label: '子ロットNo',   sortable: true},
        {key: 'serialnochild',    label: '子シリアルNo'},
        {key: 'checkdata',        label: '検査データ',   sortable: true}
      ],
      traceInfoChild: traceInfoChildData(),
      traceInfoChildFields: [
        {key: 'lotparent',        label: '親ロットNo'},
        {key: 'serialnoparent',   label: '親シリアルNo'},
        {key: 'lotchild',         label: '子ロットNo',   sortable: true},
        {key: 'serialnochild',    label: '子シリアルNo'},
        {key: 'checkdata',        label: '検査データ',   sortable: true}
      ],
      traceForward: traceForwardData(),
      traceForwardFields: [
        {key: 'linename',   label: 'ライン名'},
        {key: 'itemname',   label: '製品品番'},
        {key: 'lot',        label: '親ロットNo',   sortable: true},
        {key: 'serialno',   label: '親シリアルNo'},
        {key: 'registdate', label: '登録日',       sortable: true},
        {key: 'checkdata',  label: '検査データ',   sortable: true}
      ]
    }
  }
}
</script>
