<template>
  <v-container>
    <v-row>
      <v-col cols="12" lg="6">
        <v-menu
          ref="menu1"
          v-model="menu1"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="schedule.startDateFormatted"
              label="Date"
              hint="MM/DD/YYYY format"
              persistent-hint
              prepend-icon="mdi-calendar"
              v-bind="attrs"
              @blur="
                schedule.startDate = parseDate(schedule.startDateFormatted)
              "
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="schedule.startDate"
            no-title
            @input="menu1 = false"
          ></v-date-picker>
        </v-menu>
        <p>
          Date in ISO format: <strong>{{ schedule.startDate }}</strong>
        </p>
      </v-col>

      <v-col cols="12" lg="6">
        <v-menu
          ref="menu2"
          v-model="menu2"
          :close-on-content-click="false"
          transition="scale-transition"
          offset-y
          max-width="290px"
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="schedule.endDateFormatted"
              label="Date"
              hint="MM/DD/YYYY format"
              persistent-hint
              prepend-icon="mdi-calendar"
              v-bind="attrs"
              @blur="schedule.endDate = parseDate(schedule.endDateFormatted)"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="schedule.endDate"
            no-title
            @input="menu2 = false"
          ></v-date-picker>
        </v-menu>
        <p>
          Date in ISO format: <strong>{{ schedule.endDate }}</strong>
        </p>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data: (vm) => ({
    menu1: false,
    menu2: false,
    schedule: {
      startDate: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      startDateFormatted: vm.formatDate(
        new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
          .toISOString()
          .substr(0, 10)
      ),
      endDate: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      endDateFormatted: vm.formatDate(
        new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
          .toISOString()
          .substr(0, 10)
      ),
    },
  }),

  computed: {
    computedDateFormatted() {
      return (
        this.formatDate(this.schedule.startDate),
        this.formatDate(this.schedule.endDate)
      );
    },
  },

  watch: {
    menu1(val) {
      this.schedule.startDateFormatted = this.formatDate(this.schedule.startDate);
      this.schedule.endDateFormatted = this.formatDate(this.schedule.endDate);
      this.sentScheduleDate(this.schedule);
    },
    menu2(val) {
      this.schedule.startDateFormatted = this.formatDate(this.schedule.startDate);
      this.schedule.endDateFormatted = this.formatDate(this.schedule.endDate);
      this.sentScheduleDate(this.schedule);
    },
  },

  methods: {
    formatDate(date) {
      if (!date) return null;

      const [year, month, day] = date.split("-");
      return `${month}/${day}/${year}`;
    },
    parseDate(date) {
      if (!date) return null;

      const [month, day, year] = date.split("/");
      return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
    },
    sentScheduleDate(schedule) {
      this.$emit("schedule", schedule.startDate, schedule.endDate);
    },
  },
};
</script>