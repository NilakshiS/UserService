package com.stackroute.Muzix.controller;

import static org.mockito.ArgumentMatchers.any;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class TrackControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    private User track;
//    @MockBean
//    private UserService trackService;
//    @InjectMocks
//    private TrackController trackController;
//
//    private List<User> list =null;
//
//    @Before
//    public void setUp(){
//
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(UserControllerAdvice.class).build();
//        track = new User();
//        track.setTrackName("Love Maze");
//        track.setTrackComments("BTS");
//        list = new ArrayList<>();
//        list.add(track);
//    }
//
//    @Test
//    public void saveTrack() throws Exception {
//        when(trackService.saveTrack(any())).thenReturn(track);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//    @Test
//    public void saveTrackFailure() throws Exception {
//        when(trackService.saveTrack(any())).thenThrow(UserAlreadyExistsException.class);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void getTrack() throws Exception {
//        when(trackService.getTrackById(anyInt())).thenReturn(track);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//    @Test
//    public void getTrackFailure() throws Exception {
//        when(trackService.getTrackById(anyInt())).thenThrow(UserNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void updateTrack() throws Exception {
//        when(trackService.updateTrack(any())).thenReturn(track);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//    @Test
//    public void updateTrackFailure() throws Exception {
//        when(trackService.updateTrack(any())).thenThrow(UserNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void deleteTrack() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/1")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//
//    @Test
//    public void searchTrack() throws Exception {
//        when(trackService.getTrackByNameOrComments(any())).thenReturn(List.of(track));
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/search/abc")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }
//       @Test
//    public void getAllTracks() throws Exception {
//        when(trackService.getAllTracks()).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//
//    private static String asJsonString(final Object obj)
//    {
//        try{
//            return new ObjectMapper().writeValueAsString(obj);
//
//        }catch(Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//}
