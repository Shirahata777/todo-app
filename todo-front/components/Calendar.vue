<template>
  <div>
    <FullCalendar :options="calendarOptions" />
    <div><EventFormModal @addEventData="emitAddEventData" /></div>
  </div>
</template>

<script>
import FullCalendar from "@fullcalendar/vue";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import EventFormModal from "~/components/event-form-modal.vue";
export default {
  components: {
    EventFormModal,
    FullCalendar,
  },
  created() {
    this.calendarOptions.events.push({
      title: "event 3",
      start: "2021-04-01",
      end: "2021-04-03",
    });
  },
  data() {
    return {
      calendarOptions: {
        timeZone: "local",
        plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
        initialView: "dayGridMonth",
        dateClick: this.handleDateClick,
        events: [
          { title: "event 1", date: "2021-04-01T12:30:00Z" },
          { title: "event 2", date: "2021-04-02T12:30:00Z" },
          { title: "event 2", date: "2021-04-02T12:30:00Z" },
          { title: "event 2", date: "2021-04-02T12:30:00Z" },
        ],
        dayMaxEvents: true,
      },
      addEventData: {
        title: "",
        start: "",
        end: "",
        contents: "",
      },
    };
  },
  methods: {
    handleDateClick: function (arg) {
      alert("date click! " + arg.dateStr);
    },
    emitAddEventData(addEventData) {
      this.addEventData = addEventData;
      this.calendarOptions.events.push(addEventData)
    },
  },
};
</script>