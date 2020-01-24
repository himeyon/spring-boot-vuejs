<script>
import { Line } from 'vue-chartjs'
import { CustomTooltips } from '@coreui/coreui-plugin-chartjs-custom-tooltips'
import { hexToRgba } from '@coreui/coreui/dist/js/coreui-utilities'

export default {
  components: {
    hexToRgba,
    CustomTooltips
  },
  extends: Line,
  mounted () {
    this.renderChart(
      {
        labels: [
          '09:01', '09:02', '09:03', '09:04', '09:05', '09:06', '09:07', '09:08', '09:09', '09:10', 
          '09:11', '09:12', '09:13', '09:14', '09:15', '09:16', '09:17', '09:18', '09:19', '09:20', 
          '09:21', '09:22', '09:23', '09:24', '09:25', '09:26', '09:27', '09:28', '09:29', '09:30'
        ],
        datasets: [
          {
            label: 'MAZAK QUICK TURN350',
            backgroundColor: hexToRgba('#0000cd', 1),
            borderColor: hexToRgba('#0000cd', 80),
            data: [90, 84, 82, 83, 90, 86, 85, 82, 95, 97, 97, 98, 82, 80, 97, 85, 95, 81, 99, 96, 94, 98, 82, 92, 89, 99, 99, 97, 95, 91]
          },
          {
            label: 'LH55-N型NC旋盤',
            backgroundColor: hexToRgba('#228b22', 1),
            borderColor: hexToRgba('#228b22', 80),
            data: [98, 82, 83, 90, 93, 92, 85, 98, 83, 89, 94, 83, 95, 93, 97, 97, 88, 93, 99, 95, 94, 99, 96, 94, 90, 86, 92, 98, 85, 95]
          },
          {
            label: 'MAZAK QUICK TURN350',
            backgroundColor: hexToRgba('#ffd700', 1),
            borderColor: hexToRgba('#ffd700', 80),
            data: [88, 83, 95, 87, 80, 96, 86, 94, 89, 97, 85, 81, 83, 99, 96, 85, 92, 93, 80, 89, 92, 90, 95, 93, 94, 96, 91, 88, 80, 99]
          },
          {
            label: 'LH45-N型NC旋盤',
            backgroundColor: hexToRgba('#ff6347', 1),
            borderColor: hexToRgba('#ff6347', 80),
            data: [95, 95, 92, 89, 89, 84, 84, 91, 96, 99, 87, 91, 85, 82, 87, 83, 81, 95, 90, 90, 91, 89, 97, 83, 89, 99, 82, 90, 92, 91]
          }
        ],
      },
      {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
                min: 50,
                max: 100
              }
            }
          ]
        },
        responsive: true,
        maintainAspectRatio: true,
        tooltips: {
          enabled: false,
          custom: CustomTooltips,
          intersect: true,
          mode: 'index',
          position: 'nearest',
          callbacks: {
            labelColor: function (tooltipItem, chart) {
              return { backgroundColor: chart.data.datasets[tooltipItem.datasetIndex].backgroundColor }
            }
          }
        }
      }
    )
  }
}
</script>
