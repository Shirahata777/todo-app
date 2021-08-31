<template>
  <div>
    <FullCalendar :options="calendarOptions" />
    <div><EventFormModal  /></div>
    {{items}}
  </div>
</template>

<script>
import FullCalendar from "@fullcalendar/vue";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import EventFormModal from "~/components/ui/form-modal.vue";

export default {
  props:[
    "items",
  ],
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
        events: [],
        dayMaxEvents: true,
      },
      addEventData: {
        title: "",
        start: "",
        end: "",
        contents: "",
      },
      schedules: [{}],
    };
  },

  watch: {
    items(items) {
      this.calendarOptions.events.push({"start": items[0]["start"], "end": items[0]["end"]})
    }
  },
  methods: {
    handleDateClick: function (arg) {
      alert("date click! " + arg.dateStr);
    },
  },
};
</script>