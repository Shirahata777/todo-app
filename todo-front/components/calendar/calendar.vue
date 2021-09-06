<template>
  <div>
    <FullCalendar :options="calendarOptions" />
    <div><EventFormModal /></div>
  </div>
</template>

<script>
import FullCalendar from "@fullcalendar/vue";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import EventFormModal from "~/components/ui/form-modal.vue";

export default {
  props: ["eventDataList"],
  components: {
    EventFormModal,
    FullCalendar,
  },
  data() {
    return {
      calendarOptions: {
        timeZone: "local",
        plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
        initialView: "dayGridMonth",
        dateClick: this.handleDateClick,
        events: this.eventDataList,
        dayMaxEvents: true,
      },
      schedules: [{}],
    };
  },
  methods: {
    handleDateClick: function (arg) {
      alert("date click! " + arg.dateStr);
    },
  },
};
</script>