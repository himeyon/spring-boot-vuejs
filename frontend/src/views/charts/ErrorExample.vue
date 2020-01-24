<script>
import { Pie } from 'vue-chartjs'

export default {
  extends: Pie,
  mounted () {
    this.renderChart({
      labels: ['正常', 'ERR001', 'ERR309', 'ERR505'],
      datasets: [
        {
          backgroundColor: [
            '#00D8FF',
            '#ff0000',
            '#ff6347',
            '#ff7f50'
          ],
          data: [94, 3, 2, 1]
        }
      ]
    }, {responsive: true, maintainAspectRatio: true})
  }
}
</script>

